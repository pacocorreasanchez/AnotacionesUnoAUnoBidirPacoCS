/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Puerto;
import es.albarregas.beans.Servicio;
import es.albarregas.daofactory.DAOFactory;
import es.albarregas.dao.IGenericoDAO;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author paco
 */
@WebServlet(name = "Eleccion", urlPatterns = {"/eleccion"})
public class Eleccion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = null;

        DAOFactory daof = DAOFactory.getDAOFactory();
        IGenericoDAO gdao = daof.getGenericoDAO();

        switch (request.getParameter("op")) {
            case "add":
                url = "JSP/formularioAlta.jsp";
                break;
            case "delete":
            case "update":
            case "list":
                String entidad = "Puerto";
                List<Puerto> listaPuertos = new ArrayList<>();
                List<Object> lista = gdao.get(entidad);
                for (Object puerto : lista) {
                    listaPuertos.add((Puerto) puerto);
                }
                request.setAttribute("listaPuertos", listaPuertos);

                break;
            case "listadoServicios":
                String entidad2 = "Servicio";
                List<Servicio> listaServicios = new ArrayList<>();
                List<Object> lista2 = gdao.get(entidad2);
                for (Object servicio : lista2) {
                    listaServicios.add((Servicio) servicio);
                }
                request.setAttribute("listaServicios", listaServicios);

        }

        switch (request.getParameter("op")) {
            case "list":
                url = "JSP/listado.jsp";
                break;
            case "listadoServicios":
                url = "JSP/listadoServicios.jsp";
                break;
            case "delete":
                url = "JSP/borrado.jsp";
                break;
            case "update":
                url = "JSP/actual.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
