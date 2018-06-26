package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeast;

public class UsersControllerTest {
    @Test
    public void whenAddUser() throws ServletException, IOException {
        UsersController controller = new UsersController();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("1q");
        when(req.getParameter("login")).thenReturn("22q");
        when(req.getParameter("email")).thenReturn("333q");
        when(req.getParameter("password")).thenReturn("4444q");
        when(req.getParameter("role")).thenReturn("1");
        when(req.getParameter("action")).thenReturn("add");
        controller.doPost(req, resp);
        assertThat(DBStore.getInstance().findCredential("22q", "4444q").toString(), is("User{id=23, name='1q', login='22q', email='333q', createDate=2018-06-26 18:40:32.575}"));
    }


    @Test
    public void whenUserSignin() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn("session");
        session.setAttribute("user", "user");
        when(request.getParameter("login")).thenReturn("wr1");
        when(request.getParameter("password")).thenReturn("admin");
        when(request.getSession()).thenReturn(session);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new SigninController().doPost(request, response);
        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");

    }

    @Test
    public void whenUserResponse() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("theUser")).thenReturn(new User(6, "1", "2", "3", "4", "5",  new Timestamp(System.currentTimeMillis())));
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/views/UsersView.jsp")).thenReturn(mock(RequestDispatcher.class));
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new UsersController().doGet(request, response);
        writer.flush();
    }

    @Test
    public void whenUserResponseEcho() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getOutputStream()).thenReturn(mock(ServletOutputStream.class));
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        new EchoServlet().doGet(request, response);
        writer.flush();
        System.out.println(response.getOutputStream().toString());
    }
}
