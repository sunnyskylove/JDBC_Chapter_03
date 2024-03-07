package com.ohgiraffers.section01.insert;

import com.ohgiraffers.model.dto.MenuDTO;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBC.template.close;
import static com.ohgiraffers.common.JDBC.template.getConnection;

public class Application02 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("메뉴의 이름을 입력해주세요 : ");
        String menuName = sc.nextLine();

        System.out.print("메뉴 가격을 입력하세요 : ");
        int menuPrice = sc.nextInt();

        System.out.print("카테고리 코드를 입력해주세요 : ");
        int categoryCode = sc.nextInt();
        sc.nextLine();
        System.out.print("판매 여부를 결정해 주세요(Y/N) : ");
        // 문자열을 토큰단위로~~
        String orderableSatus = sc.nextLine().toUpperCase();
        // 소문자로 사용할 수도 있어서
        // toUpperCase로 자동으로 입력한 글자가 대문자가 되게 설정한다.

        MenuDTO newMenu = new MenuDTO();
        newMenu.setMenuName(menuName);       // (위에 입력한 menuName)
        newMenu.setMenuPrice(menuPrice);
        newMenu.setCategoryCode(categoryCode);
        newMenu.setOrderableStatus(orderableSatus);

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;   // 결과값 변수를 미리 만들어준다,

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("insertMenu");
            pstmt = con.prepareStatement(query);
            // query를 전달해서 prepareStatement 를 만들어 줄거임~

            pstmt.setString(1, newMenu.getMenuName());
            // 아까 스캐너로 set해준걸 get으로 받아오겠다.
            pstmt.setInt(2, newMenu.getMenuPrice());
            pstmt.setInt(3, newMenu.getCategoryCode());
            pstmt.setString(4, newMenu.getOrderableStatus());

            result = pstmt.executeUpdate();


        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("메뉴 등록 성공~~~");

        } else {
            System.out.println("메뉴 등록 실패~~~");

        }
    }
}

// 얘는 고쳐졌다~^^ try 이하, load -> loadFromXML