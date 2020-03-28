/**
 * A Classe Item e uma classe feita para armazenar informacoes de um item.
 *
 * @author (Victor Silva)
 * @version (1)
 */
package com.victor.silva.crawler;

import java.util.List;


class Item {

    private String Name;
    private List<String>ImageUrl;
    private String Preco_Antigo;
    private String Preco_Desconto;
    private String Preco_Normal;
    private String Desc_Preco_Prod;
    private String Desc_Produto;

    //produto em promoção

    /**
     * Construtor para o produto em promocao
     * @param Name String com o nome do produto
     * @param ImageUrl Lista do tipo String com as urls do produto
     * @param Preco_Antigo String com o preco do produto
     * @param Preco_Desconto String com o preco do produto
     * @param Preco_Normal String com o preco do produto
     * @param Desc_Preco_Prod String com a descricao do preco do produto
     * @param Desc_Produto String com a descricao do produto
     */
    public Item(String Name, List<String> ImageUrl, String Preco_Antigo, String Preco_Desconto, String Preco_Normal, String Desc_Preco_Prod, String Desc_Produto) {

        this.Name = Name;
        this.ImageUrl = ImageUrl;
        this.Preco_Antigo = Preco_Antigo;
        this.Preco_Desconto = Preco_Desconto;
        this.Preco_Normal = Preco_Normal;
        this.Desc_Preco_Prod = Desc_Preco_Prod;
        this.Desc_Produto = Desc_Produto;

    }

    //produto normal

    /**
     * Construtor para produto com preco normal
     * @param Name String com o nome do produto
     * @param ImageUrl Lista do tipo String com as urls do produto
     * @param Preco_Antigo String com o preco do produto
     * @param Preco_Desconto String com o preco do produto
     * @param Desc_Preco_Prod String com a descricao do preco do produto
     * @param Desc_Produto String com a descricao do produto
     */
    public Item(String Name, List<String> ImageUrl, String Preco_Antigo, String Preco_Desconto, String Desc_Preco_Prod, String Desc_Produto){
        this.Name = Name;
        this.ImageUrl = ImageUrl;
        this.Preco_Antigo = Preco_Antigo;
        this.Preco_Desconto = Preco_Desconto;
        this.Desc_Preco_Prod = Desc_Preco_Prod;
        this.Desc_Produto = Desc_Produto;
    };

    public Item(String Name, List<String> ImageUrl, String Preco_Desconto, String Desc_Preco_Prod, String Desc_Produto){
        this.Name = Name;
        this.ImageUrl = ImageUrl;
        this.Preco_Desconto = Preco_Desconto;
        this.Desc_Preco_Prod = Desc_Preco_Prod;
        this.Desc_Produto = Desc_Produto;
    };
//metodo para transformar em json

    /**
     * Obtem o nome Item
     * @return retorna o nome do item
     */
    public String getName() {
        return Name;
    }

    /**
     * Altera o nome do item
     * @param name string com nome do item
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Obtem a lista de urls do item
     * @return retorna a lista de url das imagens do item
     */
    public List<String> getImageUrl() {
        return ImageUrl;
    }

    /**
     * Altera a lista de url do item
     * @param imageUrl lista de url das imagens do item
     */
    public void setImageUrl(List<String> imageUrl) {
        ImageUrl = imageUrl;
    }

    /**
     * Obtem o preco do item
     * @return retorna o preco do item
     */
    public String getPreco_Antigo() {
        return Preco_Antigo;
    }

    /**
     * Altera o preco do item
     * @param preco_Antigo string com o preco do item
     */
    public void setPreco_Antigo(String preco_Antigo) {
        Preco_Antigo = preco_Antigo;
    }

    /**
     * Obtem o preco do item
     * @return retorna o preco do item
     */
    public String getPreco_Desconto() {
        return Preco_Desconto;
    }

    /**
     * Altera o preco do item
     * @param preco_Desconto string com o preco do item
     */
    public void setPreco_Desconto(String preco_Desconto) {
        Preco_Desconto = preco_Desconto;
    }

    /**
     * Obtem o preco do item
     * @return retorna o preco do item
     */
    public String getPreco_Normal() {
        return Preco_Normal;
    }

    /**
     * Altera o preco do item
     * @param preco_Normal string com o preco do item
     */
    public void setPreco_Normal(String preco_Normal) {
        Preco_Normal = preco_Normal;
    }

    /**
     * Obtem o preco do item
     * @return retorna o preco do item
     */
    public String getDesc_Produto() {
        return Desc_Produto;
    }

    /**
     * Altera o preco do item
     * @param desc_Produto string com o preco do item
     */
    public void setDesc_Produto(String desc_Produto) {
        Desc_Produto = desc_Produto;
    }

    /**
     * Obtem o preco do item
     * @return retorna o preco do item
     */
    public String getDesc_Preco_Prod() {
        return Desc_Preco_Prod;
    }

    /**
     * Altera o preco do item
     * @param desc_Preco_Prod string com o preco do item
     */
    public void setDesc_Preco_Prod(String desc_Preco_Prod) {
        Desc_Preco_Prod = desc_Preco_Prod;
    }

    /**
     * Monta e retorna uma string com os detalhes do item
     * diferenciando para quando é um item de promocao
     * ou quando é um item de preco normal
     * @return retorna a descricao do item
     */
    public String toString(){
        String imgUrls="";
        for (String el: getImageUrl()) {
            imgUrls+= "\n"+el+"\n";
        }
        if(getPreco_Normal()!=null){
            return getName()+"\n\nPreco Original: "+getPreco_Antigo()+
                    "\nPreco Desconto: "+getPreco_Desconto()+
                    "\nPreco a Vista: "+getPreco_Normal()+
                    "\n"+getDesc_Preco_Prod()+
                    "\n\nDescrição do Produto: \n"+getDesc_Produto()+
                    "\n\nURL das Imagens do produto: "+imgUrls;
        }
        else if(getPreco_Antigo()!=null){
            return getName()+"\n\nPreco Original: "+getPreco_Antigo()+
                    "\nPreco a Vista: "+getPreco_Desconto()+
                    "\n"+getDesc_Preco_Prod()+
                    "\n\nDescrição do Produto: \n"+getDesc_Produto()+
                    "\n\nURL das Imagens do produto: \n"+imgUrls;
        }
        else
        {
            return getName()+ "\n\nPreco a Vista: "+getPreco_Desconto()+
                    "\n"+getDesc_Preco_Prod()+
                    "\n\nDescrição do Produto: \n"+getDesc_Produto()+
                    "\n\nURL das Imagens do produto: \n"+imgUrls;
        }
    }
}
