package com.hugh.javatest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.io.FileReader;
import javax.script.Invocable;

public class MyMemo {
    private final static String jsFileName= "E:\\Workspace\\HughWeb\\img\\bak\\encrypt.js";
    private final static String dbFileName = "E:\\Workspace\\HughWeb\\img\\#Hugh.bin";
    private final static String charCode="UTF-8";
    
    public class Encrypt{

        private String key;
        private ScriptEngine engine=new ScriptEngineManager().getEngineByExtension("js");

        public void setKey(String id) {
            key=id+"^des3";
        }

        public String encode(String str) throws Exception{
            if(str==null || "".equals(str)) return "";
            engine.eval(new FileReader(jsFileName));
            Invocable inv=(Invocable)engine;
            return String.valueOf(inv.invokeFunction("encrypt",new String[]{key,str}));
        }
        
        public String decode(String str) throws Exception{
            if(str==null || "".equals(str)) return "";
            engine.eval(new FileReader(jsFileName));
            Invocable inv=(Invocable)engine;
            return String.valueOf(inv.invokeFunction("decrypt",new String[]{key,str}));
        }
    
        public void test() throws Exception {
            setKey("29");
            String str="strive*429#";
            engine.eval(new FileReader(jsFileName));
            Invocable inv=(Invocable)engine;
            String cipher=String.valueOf(inv.invokeFunction("encrypt",new String[]{key,str}));
            String plaintext=String.valueOf(inv.invokeFunction("decrypt",new String[]{key,cipher}));
            System.out.println(cipher+","+plaintext);
        }
    }
    
    public static void main(String[] args) throws Exception {
        int execType=2;//1encrypt��2decypt
        int isExec=0;//�Ƿ�ִ��
        Encrypt encrypt=new MyMemo().new Encrypt();
        encrypt.test();
        
        String id,userName,userPwd,tel,email,memo;
        Connection connection = null;
        String sql="select * from tb_acct order by id";
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+dbFileName);
            Statement statQuery = connection.createStatement();
            Statement statExec = connection.createStatement();
            statQuery.setQueryTimeout(10); // set timeout to 10 sec.
            statExec.setQueryTimeout(10); // set timeout to 10 sec.
            ResultSet rs = statQuery.executeQuery(sql);
            int rowno=0;
            while (rs.next()) {
                ++rowno;
                id=rs.getString("ID");
                userName=rs.getString("USER_NAME");
                userPwd=rs.getString("USER_PWD");
                tel=rs.getString("TEL");
                email=rs.getString("EMAIL");
                memo=rs.getString("MEMO");
                encrypt.setKey(id);
                System.out.printf("����ǰ ID:%4s,USER_NAME:"+userName+",USER_PWD:"+userPwd+",TEL:"+tel+",EMAIL:"+email+",MEMO:"+memo+"\n",id);
                //����
                if(execType==1){
                    //userName=encrypt.encode(userName);
                    userPwd=encrypt.encode(userPwd);
                    //tel=encrypt.encode(tel);
                    //email=encrypt.encode(email);
                    memo=encrypt.encode(memo);
                    System.out.printf("���ܺ� ID:%4s,USER_NAME:"+userName+",USER_PWD:"+userPwd+",TEL:"+tel+",EMAIL:"+email+",MEMO:"+memo+"\n",id);
                    sql="update tb_acct set USER_NAME='"+userName+"',USER_PWD='"+userPwd+"',TEL='"+tel+"',EMAIL='"+email+"',MEMO='"+memo+"' where id="+id;
                }
                //����
                else if(execType==2){
                    //userName=encrypt.decode(userName);
                    userPwd=encrypt.decode(userPwd);
                    //tel=encrypt.decode(tel);
                    //email=encrypt.decode(email);
                    memo=encrypt.decode(memo);
                    System.out.printf("���ܺ� ID:%4s,USER_NAME:"+userName+",USER_PWD:"+userPwd+",TEL:"+tel+",EMAIL:"+email+",MEMO:"+memo+"\n",id);
                    sql="update tb_acct set id="+id+",USER_NAME='"+userName+"',USER_PWD='"+userPwd+"',TEL='"+tel+"',EMAIL='"+email+"',MEMO='"+memo+"' where id="+id;
                }
                if(isExec==1) statExec.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
            }
        }
    }
}
