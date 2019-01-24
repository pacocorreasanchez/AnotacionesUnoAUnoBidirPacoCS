package es.albarregas.controllers;

import es.albarregas.beans.Puerto;
import es.albarregas.beans.Servicio;
import es.albarregas.daofactory.DAOFactory;
import es.albarregas.dao.IGenericoDAO;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author paco
 */
@WebServlet(name = "Controlador", urlPatterns = {"/control"})
public class Controlador extends HttpServlet {

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
        
        DAOFactory daof = DAOFactory.getDAOFactory();
        IGenericoDAO gdao = daof.getGenericoDAO();
        
        Puerto puerto = new Puerto();
        Servicio servicio = new Servicio();
        
        String url = null;
        
        switch (request.getParameter("op")) {
            case "add":
                puerto.setNumero(Integer.parseInt(request.getParameter("numero")));
                puerto.setTipo(request.getParameter("tipo"));
                
                servicio.setNombre(request.getParameter("nombre"));
                servicio.setPath(request.getParameter("path"));
                
                gdao.add(puerto);
                gdao.add(servicio);
                url = "index.html";
                break;
            case "delete":
                puerto = (Puerto) gdao.getOne(puerto.getClass(), Long.parseLong(request.getParameter("registro")));
                gdao.delete(puerto);
                url = "index.html";
                break;
            case "update":
                puerto = (Puerto) gdao.getOne(puerto.getClass(), Long.parseLong(request.getParameter("registro")));
                request.setAttribute("puerto", puerto);
                url = "JSP/formularioActualizar.jsp";
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
