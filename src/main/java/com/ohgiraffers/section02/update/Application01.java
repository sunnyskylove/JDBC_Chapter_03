package com.ohgiraffers.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBC.template.close;
import static com.ohgiraffers.common.JDBC.template.getConnection;
import static java.awt.SystemColor.menu;

public class Application01 {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("updateMenu");
            // 새로운 "updateMenu" 이름으로 커리문 만들기~~ // 기존 menu-query.xml에 추가하기~

            /* MENU_CODE, MENU_PRICE, MENU_CODE */
            Scanner sc = new Scanner(System.in);
            System.out.println("변경할 메뉴코드를 입력해 주세요. :");
            int menuCode = sc.nextInt();
            System.out.println("변경할 메뉴의 이름을 입력해 주세요 : ");
            String menuName = sc.nextLine();
            sc.nextLine();      // 떨어져서 나오게~~(공백)
            System.out.println("변경할 메뉴의 가격을 입력해주세요 :");
            int menuPrice = sc.nextInt();

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,menuName);
            pstmt.setInt(2,menuPrice);
            pstmt.setInt(3,menuCode);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);


        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {

            close(con);
            close(pstmt);
        }

        if (result > 0) {
            System.out.println("메뉴 수정 성공!! ");
        } else {
            System.out.println("메뉴 수정 실패!!");
        }

    }

}

// 얘는 계속 오류나서 안된다..ㅠㅠ
// >>>> menu_query<set>에서 마지막 orderable_status 1개 지우니 오류해결됐다...0_0
