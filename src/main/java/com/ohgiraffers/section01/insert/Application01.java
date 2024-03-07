package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBC.template.close;
import static com.ohgiraffers.common.JDBC.template.getConnection;

public class Application01 {

    public static void main(String[] args) {

        Connection con = getConnection();               // 연결준비!

        PreparedStatement pstmt = null;

        int result = 0;
        // 데이터베이스 내에서 행이 바뀌게 되면, 행이 하나 추가되거나 삭제되거나 바뀌거나 변경될때~

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            // 경로대로 읽을 것이다!

            String query = prop.getProperty("insertMenu");
            // key 값 불러올 수 있다. "insertMenu" 키 가져옴(menu-query.xml)

            System.out.println("query = " + query);

//            Scanner sc = new Scanner(System.in);
//            System.out.println("등록할 메뉴 이름 :");
//            String menuName = sc.nextLine();
            // 스캐너 넣어서 추가로 사용할 수도 있다.


            pstmt = con.prepareStatement(query);

            pstmt.setString(1, "봉골레청국장"); // 1번째 값에는 "봉골레청국장"이 들어간다.
            pstmt.setInt(2, 50000);      // 2번째 값에는 5만원이 들어감
            pstmt.setInt(3, 1);
            pstmt.setString(4, "Y");

            result = pstmt.executeUpdate();


        } catch (IOException e) {
            throw new RuntimeException(e);


        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {

            close(con);
            close(pstmt);
        }

        System.out.println("result = " + result);

    }
}
