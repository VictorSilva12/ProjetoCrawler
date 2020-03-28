package com.victor.silva.crawler;

import java.util.List;


class Item {

    private String Name; //id #Name
    private List<String>ImageUrl; //id #imagem-slide | pegar o primeiro item (li) da lista (ul)
    private String Preco_Antigo; //class .preco_antigo-cm
    private String Preco_Desconto; //class .preco_desconto-cm
    private String Preco_Normal; //class .preco_normal-cm
    private String Desc_Preco_Prod;
    private String Desc_Produto; //div.content_tab > p

    //produto em promoção
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
    public Item(String Name, List<String> ImageUrl, String Preco_Antigo, String Preco_Desconto, String Desc_Preco_Prod, String Desc_Produto){
        this.Name = Name;
        this.ImageUrl = ImageUrl;
        this.Preco_Antigo = Preco_Antigo;
        this.Preco_Desconto = Preco_Desconto;
        this.Desc_Preco_Prod = Desc_Preco_Prod;
        this.Desc_Produto = Desc_Produto;
    };
//metodo para transformar em json

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getPreco_Antigo() {
        return Preco_Antigo;
    }

    public void setPreco_Antigo(String preco_Antigo) {
        Preco_Antigo = preco_Antigo;
    }

    public String getPreco_Desconto() {
        return Preco_Desconto;
    }

    public void setPreco_Desconto(String preco_Desconto) {
        Preco_Desconto = preco_Desconto;
    }

    public String getPreco_Normal() {
        return Preco_Normal;
    }

    public void setPreco_Normal(String preco_Normal) {
        Preco_Normal = preco_Normal;
    }

    public String getDesc_Produto() {
        return Desc_Produto;
    }

    public void setDesc_Produto(String desc_Produto) {
        Desc_Produto = desc_Produto;
    }


    public String getDesc_Preco_Prod() {
        return Desc_Preco_Prod;
    }

    public void setDesc_Preco_Prod(String desc_Preco_Prod) {
        Desc_Preco_Prod = desc_Preco_Prod;
    }

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
        else{
            return getName()+"\n\nPreco Original: "+getPreco_Antigo()+
                    "\nPreco a Vista: "+getPreco_Desconto()+
                    "\n"+getDesc_Preco_Prod()+
                    "\n\nDescrição do Produto: \n"+getDesc_Produto()+
                    "\n\nURL das Imagens do produto: \n"+imgUrls;
        }
    }
}
