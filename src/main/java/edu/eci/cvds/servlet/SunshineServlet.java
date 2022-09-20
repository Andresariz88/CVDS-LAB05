package edu.eci.cvds.servlet;

import edu.eci.cvds.servlet.model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.*;
import java.util.Optional;

@WebServlet(urlPatterns = "/sunshine")

public class SunshineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer responseWriter = resp.getWriter();
        Optional<String> optId = Optional.ofNullable(req.getParameter("id"));
        String id = optId.isPresent() && !optId.get().isEmpty() ? optId.get() : "";
        try {
            Todo todo = Service.getTodo(Integer.parseInt(id));
            List<Todo> todos = new ArrayList<Todo>();
            todos.add(todo);
            String msg = Service.todosToHTMLTable(todos);
            resp.setStatus(HttpServletResponse.SC_OK);
            responseWriter.write(msg);
        } catch (FileNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            responseWriter.write("No encontrado");
        } catch (NumberFormatException e) {
            responseWriter.write("Requerimiento inválido");
        } catch (MalformedURLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseWriter.write("Error interno en el servidor");
        } catch (Exception e) {
            responseWriter.write("Requerimiento inválido.");
        } finally {
            responseWriter.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer responseWriter = resp.getWriter();
        Optional<String> optId = Optional.ofNullable(req.getParameter("id"));
        String id = optId.isPresent() && !optId.get().isEmpty() ? optId.get() : "";
        try {
            Todo todo = Service.getTodo(Integer.parseInt(id));
            List<Todo> todos = new ArrayList<Todo>();
            todos.add(todo);
            String msg = Service.todosToHTMLTable(todos);
            resp.setStatus(HttpServletResponse.SC_OK);
            responseWriter.write(msg);
        } catch (FileNotFoundException e) {
            responseWriter.write("No encontrado");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (NumberFormatException e) {
            responseWriter.write("Requerimiento inválido");
        } catch (MalformedURLException e) {
            responseWriter.write("Error interno en el servidor");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            responseWriter.write("Requerimiento inválido.");
        } finally {
            responseWriter.flush();
        }
    }
}
