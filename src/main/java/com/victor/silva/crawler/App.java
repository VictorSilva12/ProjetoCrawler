/**
 * A Classe App e uma classe criada para fornecer um menu
 * e para a exibição de informações.
 *
 * @author (Victor Silva)
 * @version (1)
 */
package com.victor.silva.crawler;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class App
{
    /**
     * Metodo Principal, onde e apresentado o menu onde o usuario pode inserir
     * a url do produto que ele deseja extrair as informacoes.
     * @param args
     */
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

                        JTextArea areaTexto = new JTextArea(30, 70);
                        areaTexto.setText("\n" + produto.toString());

                        areaTexto.setWrapStyleWord(true);
                        areaTexto.setLineWrap(true);
                        areaTexto.setCaretPosition(0);
                        areaTexto.setEditable(false);

                        JScrollPane janela = new JScrollPane(areaTexto);
                        JOptionPane.showMessageDialog(null, janela);
                    } catch (Exception e) {
                        System.out.println(e);
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
