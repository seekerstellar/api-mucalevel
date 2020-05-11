package com.seeker.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.seeker.entities.Personagem;

public class UtilsPerson {
	
    public static List<Personagem> getCharsGuild(String guild) {
        List<String> htmlLines = getAllPages(guild);
        List<String> linesRefinated = refinarStrings(htmlLines);

        return getPersonagens(linesRefinated);
    }

    public static Personagem findPersonagem(List<Personagem> list, String name) {

        for (Personagem x : list) {
            if (x.getName().toLowerCase().equals(name.toLowerCase())) {
                return x;
            }
        }

        return null;
    }

    private static List<String> getAllPages(String guild) {

        List<String> htmlLines = getHtmlLines(guild, 1);
        int tam = qtdPages(htmlLines);

        if (tam > 1) {
            for (int i = 2; i <= tam; i++) {
                htmlLines.addAll(getHtmlLines(guild, i));
            }
        }

        return htmlLines;
    }

    private static int qtdPages(List<String> html) {
        int p = 1;

        for (String x : html) {
            int len = x.length();
            if (len > 10) {
                String cortA = x.substring(len - 9, len - 8);
                if (Character.isDigit(cortA.charAt(0))) {
                    p = Integer.parseInt(cortA);
                }
            }
        }

        return p;
    }

    private static List<Personagem> getPersonagens(List<String> strings) {

        List<Personagem> lista = new ArrayList<>();

        for (String x : strings) {
            String splited[] = x.split(",");
            Personagem personagem = new Personagem(splited[0], splited[1], splited[2]);
            if (!containsName(lista, personagem.getName())) {
                lista.add(personagem);
            }
        }

        return lista;
    }

    private static boolean containsName(final List<Personagem> list, final String name) {
        return list.stream().anyMatch(o -> o.getName().equals(name));
    }

    private static List<String> getHtmlLines(String guild, int nPage) {

        List<String> lines = new ArrayList<>();

        try {
            URL url = new URL("https://www.mucabrasil.com.br/?go=guild&n=" + guild + "&p=" + nPage);
            InputStream is = url.openConnection().getInputStream();
            int ptr;
            String aux = "";

            while ((ptr = is.read()) != -1) {
                char charAtual = (char) ptr;
                if (charAtual == '\n') {
                    lines.add(aux);
                    aux = "";
                }
                aux += charAtual;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return lines;
    }

    private static List<String> refinarStrings(List<String> lines) {

        List<String> lista = new ArrayList<>();

        String aux = "";

        int counterTD = 0;
        int counterINFO = 0;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.substring(1, 5).equals("<td>")) {

                counterTD++;
                char characterMain = line.charAt(4);
                String stringRecort = line.length() > 6 ? recortString(line) : "";

                if (!stringRecort.equals("")
                        && !stringRecort.equals("Senha:")
                        && !stringRecort.equals("Login:")
                        && characterMain != '<'
                        && counterTD > 10) {

                    counterINFO++;
                    aux += stringRecort + ",";
                    
                    if (counterINFO == 3) {
                        lista.add(aux.substring(0, aux.length() - 1));
                        aux = "";
                        counterINFO = 0;
                    }

                }
            }
        }

        return lista;
    }

    private static String recortString(String string) {
        int p = -1;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '<' && i > 1) {
                p = i;
                break;
            }
        }

        return string.substring(5, p);
    }

    public static String getTime(SimpleDateFormat sdf) {
        Date data = new Date();
        return sdf.format(data);
    }
}
