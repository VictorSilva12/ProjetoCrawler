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
    //recebe url base e o tipo do elemento String Url, String tipoEl public Item ElementSearch()
    public Item ProcurarElemento(String Url){

        Document doc = null;

        try {
            //captura o html da url escolhida
            doc = Jsoup.connect(Url).get();
            /*  verifica se o item da pagina pesquisada está em promoção
                existe uma diferença entre os html, por isso a necessidade dessa
                verificação*/
            if(tipoValor(doc).equals("promoção")){
                //chama o construtor para o item em promoção
                Item produto = new Item(nomeProduto(doc), Imagens(doc), Precos(doc).remove(1), Precos(doc).remove(2), Precos(doc).remove(3), Precos(doc).remove(4), DescProd(doc));
                return produto;

            }
            else{
                //chama o construtor para o item normal
                Item produto = new Item(nomeProduto(doc), Imagens(doc), Precos(doc).remove(1), Precos(doc).remove(2), Precos(doc).remove(3), DescProd(doc) );
                return produto;
            }

        } catch (IOException  e) {

            System.out.println(e.toString());

        }

        return null;
    }
    private String tipoValor(Document doc){

        if(doc.select("div.box_comprar-cm").size() != 0 ){

            return "promoção";
        }
        else if(doc.select("div.box_comprar").size() != 0){

            return "normal";

        }
        return null;
    }

    private String nomeProduto(Document doc){

        Elements NomeProd = doc.select("div#titulo_det > h1");
        return NomeProd.text();

    }

    private List<String> Imagens(Document doc){

        //verificar se o elemento não está vazio
        List <String> ListaImagensProd = new ArrayList<String>();
        Elements ImagensProd = doc.select("div#slider > ul#imagem-slide > li > img");

        for ( Element el : ImagensProd) {

            ListaImagensProd.add(el.attr("src"));

        }
        return ListaImagensProd;
    }

    private List<String> Precos(Document doc){

        if(tipoValor(doc).equals("promoção")){

            List <String> result = new ArrayList<String>();
            result.add("promoção");

            result.add(doc.select("div.preco_antigo-cm").text().split(" ")[1]+" "+doc.select("div.preco_antigo-cm").text().split(" ")[2]);//Preço Original
            result.add(doc.select("div.preco_desconto-cm > span > strong").text());//Preço com promoção
            result.add(doc.select("div.preco_normal-cm > span[itemprop =\"offers\"] > span.preco_desconto_avista-cm").text());//Preço com promoção e desconto
            result.add(doc.select("div.preco_normal-cm").text());
            return result;
        }
        else if(tipoValor(doc).equals("normal")){

            List <String> result = new ArrayList<String>();
            result.add("normal");
            result.add(doc.select("div.box_preco > div.preco_traco > div.preco_antigo").text().split(" ")[1]+" "+doc.select("div.box_preco > div.preco_traco > div.preco_antigo").text().split(" ")[2]);//Preço Original
            result.add(doc.select("div.box_preco > div.preco_traco > span.preco_desconto > span > span[itemprop =\"offers\"] > strong").text());//Preço com Desconto
            result.add(doc.select("div.box_preco > div.preco_traco").text().split("por")[1]);//Descrição do produto

            return result;

        }
        else{
            return null;
        }
    }

    private String DescProd(Document doc){

        Elements desc = doc.select("div.tab_ > div.content_tab > p[itemprop =\"description\"] ");

        return desc.text();
    }

}

