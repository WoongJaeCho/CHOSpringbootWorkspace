package kr.boot.basic.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "basicServlet" , urlPatterns = "/basic")
public class BasicServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
        System.out.println("BasicServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        // 쿼리파라미터를 읽을 수 있다

        String username = request.getParameter("username");
        System.out.println("username = " + username);


        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
