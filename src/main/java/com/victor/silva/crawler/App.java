package com.victor.silva.crawler;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class App
{
    public static void main( String[] args )
    {
        int op=0;
        do{

            String menu1 = "Menu\nDigite 1 no campo abaixo para sair\n\nEscolha qualquer produto do E-Comercer\nKabum (https://www.kabum.com.br/) e insira\na URL deste produto no campo abaixo e clique\nem ok para pesquisar.";
            String URL = JOptionPane.showInputDialog(null, menu1);
            if(!URL.equals("")){
                if(!URL.equals("1")) {
                    try {
                        Crawler teste = new Crawler();
                        Item produto = teste.ProcurarElemento(URL);
                        System.out.println(produto.toString());

                        JTextArea areaTexto = new JTextArea(30, 70);
                        areaTexto.setText("\n" + produto.toString());

                        areaTexto.setWrapStyleWord(true);
                        areaTexto.setLineWrap(true);
                        //pesquisar o que faz
                        areaTexto.setCaretPosition(0);
                        areaTexto.setEditable(false);

                        JScrollPane janela = new JScrollPane(areaTexto);
                        JOptionPane.showMessageDialog(null, janela);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "URL invalida");
                    }
                }else {
                    System.exit(0);
                }

            }
            else if(URL==null || URL.equals("1")){
                System.exit(0);
            }
            else{
                JOptionPane.showMessageDialog(null,"O campo não pode estar vazio");
            }

        }while (op!=1);

    }
}
