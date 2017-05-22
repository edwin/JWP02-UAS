/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubl.jwp.servlet;

import com.ubl.jwp.service.DosenService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edwin < edwinkun at gmail dot com >
 */
public class AddDosenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        new DosenService().saveDosen(request.getParameter("iddosen"), request.getParameter("namadosen"));
        String jsonObject = "{\"success\": 1}";
        
        out.print(jsonObject);
        out.flush();
    }
    
}
