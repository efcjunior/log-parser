import java.net.*;  /*incluir esta linha entre os outros imports*/  

...

private static java.io.BufferedReader sendHttpsMethod(String urlAction, String
        soapXml, java.net.URL url) throws IOException {

        /* Inicio Configuração do proxy*/
        final String authUser = "1"; //remove linha se não tem usuario
        final String authPassword = "1";//remove linha se não tem senha

        Authenticator.setDefault(
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                authUser, authPassword.toCharArray());
                    }
                }
        );

        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
        System.setProperty("https.proxyHost", "localhost");//substituir localhost pelo ip/nome servidor proxy
        System.setProperty("https.proxyPort", "8888");//substituir 8888 pela port do servidor proxy
        System.setProperty("https.proxyUser", authUser); //remove linha se não tem usuario
        System.setProperty("https.proxyPassword", authPassword);//remove linha se não tem senha
        /*Fim Configuração do proxy*/

        java.io.BufferedReader rd = null;
        HttpsURLConnection con;
        con = (HttpsURLConnection) url.openConnection();
        con.setConnectTimeout(10000);
        con.setReadTimeout(20000);
        con.setRequestProperty("Content-type", "text/xml; charset=utf8");
        con.setRequestProperty("SOAPAction", urlAction);
        con.setDoOutput(true);
        java.io.OutputStreamWriter wr = new
                java.io.OutputStreamWriter(con.getOutputStream());
        wr.write(soapXml);
        wr.flush();
        // Read the response
        rd = new java.io.BufferedReader(new
                java.io.InputStreamReader(con.getInputStream()));
        return rd;
    }
