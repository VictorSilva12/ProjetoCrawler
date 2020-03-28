/**
 * A Classe Crawler e uma classe feita para procurar e
 * extrair informações de uma pagina Html.
 *
 * @author (Victor Silva)
 * @version (1)
 */
package com.victor.silva.crawler;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.UnknownHostException;

public class Crawler {

    /**
     * Metodo principal, onde e feita a captura do html da pagina
     * e feita a extração das informações para a criacao de um objeto
     * do tipo item, que armazena as informacoes do produto escolhido.
     * @param Url String com a URL da pagina do produto.
     * @return retorna um objeto item, que contem as informacoes do produto.
     */
    //Recebe url base e o tipo do elemento String Url, String tipoEl public Item ElementSearch()
    public Item ProcurarElemento(String Url){

        Document doc = null;

        try {
            //Captura o html da url escolhida
            doc = Jsoup.connect(Url).get();
            //System.out.println(doc);
            /*  Verifica se o item da pagina pesquisada está em promoção
                existe uma diferença entre os html, por isso a necessidade dessa
                verificação*/
            if(tipoValor(doc).equals("promoção")){
                //Chama o construtor para o item em promoção
                Item produto = new Item(nomeProduto(doc), Imagens(doc), Precos(doc).remove(1), Precos(doc).remove(2), Precos(doc).remove(3), Precos(doc).remove(4), DescProd(doc));
                return produto;

            }
            else if(tipoValor(doc).equals("puro")){
                //Chama o construtor para o item em puro
                Item produto = new Item(nomeProduto(doc), Imagens(doc), Precos(doc).remove(1), Precos(doc).remove(2), DescProd(doc) );
                return produto;
            }
            else{
                //Chama o construtor para o item normal/antigo
                Item produto = new Item(nomeProduto(doc), Imagens(doc), Precos(doc).remove(1), Precos(doc).remove(2), Precos(doc).remove(3), DescProd(doc) );
                return produto;
            }

        } catch (IOException  e) {

            System.out.println(e.toString());

        }

        return null;
    }

    /**
     * Extrai da pagina do produto se o produto está ou nao em promocao.
     * @param doc Objeto do tipo Document que contem o html da pagina do produto
     * @return retorna uma string com tipo do valor do produto.
     */
    //Recebe o objeto doc do tipo Document, que contem o html da pagina do produto
    private String tipoValor(Document doc){

        if(doc.select("div.box_comprar-cm > div.content > div.box_preco-cm > div.preco_antigo-cm").size() != 0 ){

            return "promoção";
        }
        else if(doc.select("div.box_comprar > div.box_preco > div.preco_traco > div.preco_antigo").size() != 0){

            return "antigo";

        }
        else if(doc.select("div.box_comprar > div.box_preco > div.preco_traco > div.preco_normal").size() != 0) {

            return "normal";

        }
        else if(doc.select("div.box_comprar > div.box_preco > div.preco_traco > div.preco_desconto").size() != 0) {

            return "puro";
        }

        return null;
    }

    /**
     * Extrai da pagina do produto o nome do produto
     * @param doc Objeto do tipo Document que contem o html da pagina do produto
     * @return retorna a string com nome do produto
     */
    private String nomeProduto(Document doc){

        Elements NomeProd = doc.select("div#titulo_det > h1");
        return NomeProd.text();

    }

    /**
     * Extrai da pagina do produto a url das imagens do produto e cria uma
     * lista com as urls encontradas.
     * @param doc Objeto do tipo Document que contem o html da pagina do produto
     * @return retorna uma lista de url das imagens do produto.
     */
    private List<String> Imagens(Document doc){

        //verificar se o elemento não está vazio
        List <String> ListaImagensProd = new ArrayList<String>();
        Elements ImagensProd = doc.select("div#slider > ul#imagem-slide > li > img");

        for ( Element el : ImagensProd) {

            ListaImagensProd.add(el.attr("src"));

        }
        return ListaImagensProd;
    }

    /**
     * Extrai da pagina do produto a url das imagens do produto e cria uma
     * lista com as urls encontradas.
     * @param doc Objeto do tipo Document que contem o html da pagina do produto
     * @return retorna uma lista com os preços disponiveis para o produto.
     */
    private List<String> Precos(Document doc){
        //Retorna os preços para quando o produto está em promoção.

        if(tipoValor(doc).equals("promoção")){
            //System.out.println("promoção");
            List <String> result = new ArrayList<String>();
            result.add("promoção");
            result.add(doc.select("div.preco_antigo-cm").text().split(" ")[1]+" "+doc.select("div.preco_antigo-cm").text().split(" ")[2]);//Preço Original
            result.add(doc.select("div.preco_desconto-cm > span > strong").text());//Preço com promoção
            result.add(doc.select("div.preco_normal-cm > span[itemprop =\"offers\"] > span.preco_desconto_avista-cm").text());//Preço com promoção e desconto
            result.add(doc.select("div.preco_normal-cm").text());

            return result;
        }
        //Retorna os preços para quando o produto está com o preço normal.
        else if(tipoValor(doc).equals("normal")){
            //System.out.println("normal");
            List <String> result = new ArrayList<String>();
            result.add("normal");
            result.add(doc.select("div.box_preco > div.preco_traco > div.preco_normal").text());//Preço Original
            result.add(doc.select("div.box_preco > div.preco_traco > span.preco_desconto > span > span[itemprop =\"offers\"] > strong").text());//Preço com Desconto
            result.add(doc.select("div.box_preco > div.preco_traco > div.preco_desconto").text());//Descrição do produto

            return result;

        }
        else if(tipoValor(doc).equals("antigo")){
            //System.out.println("antigo");
            List <String> result = new ArrayList<String>();
            result.add("antigo");
            result.add(doc.select("div.box_preco > div.preco_traco > div.preco_antigo").text().split(" ")[1]+" "+doc.select("div.box_preco > div.preco_traco > div.preco_antigo").text().split(" ")[2]);
            result.add(doc.select("div.box_preco > div.preco_traco > span.preco_desconto > span > span[itemprop =\"offers\"] > strong").text());//Preço com Desconto
            result.add(doc.select("div.box_preco > div.preco_traco > div.preco_desconto").text());//Descrição do produto

            return result;

        }
        else if(tipoValor(doc).equals("puro")){
            //System.out.println("puro");
            List <String> result = new ArrayList<String>();
            result.add("puro");
            result.add(doc.select("div.box_preco > div.preco_traco > span.preco_desconto > span > span[itemprop =\"offers\"] > strong").text());//Preço com Desconto
            result.add(doc.select("div.box_preco > div.preco_traco > div.preco_desconto").text());//Descrição do produto

            return result;

        }

        else{
            return null;
        }

    }

    /**
     * Extrai da pagina do produto a descricao do produto.
     * @param doc Objeto do tipo Document que contem o html da pagina do produto
     * @return retorna uma string com a descricao do produto.
     */
    private String DescProd(Document doc){

        Elements desc = doc.select("div.tab_ > div.content_tab > p[itemprop =\"description\"] ");

        return desc.text();
    }

}

