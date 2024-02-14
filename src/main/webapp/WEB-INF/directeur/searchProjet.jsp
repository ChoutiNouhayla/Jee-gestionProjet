<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="Models.Projet" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recherche de Projet</title>
    <style>
        body {
            margin: auto;
            color: #ffffff;
            font: 600 16px/18px 'Open Sans', sans-serif;
            position: relative;
        }

        .background-container {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: url(prj.jpeg) center/cover;
            filter: blur(3px);
            z-index: -1;
        }

        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 1;
        }

        .container {
            text-align: center;
            margin-bottom: 10px;
            position: relative;
            margin-top: 20px;
            z-index: 2;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
            max-width: 500px;
            margin: auto;
        }

        label {
            margin-top: 10px;
            font-size: 18px;
            color: #ffffff;
            text-align: left;
            width: 100%;
        }

        input,
        select {
            width: 100%;
            padding: 10px;
            margin: 10px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            background: rgba(255, 255, 255, 0.3);
            color: #ffffff;
        }

        input::placeholder,
        select::placeholder {
            color: #ffffff;
        }

        button {
            font-size: 20px;
            padding: 5px 10px;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background: rgba(40, 57, 101, 0.6);
            margin-top: 5px;
            width: 70%;
            height: 30%;
        }

        button.cancel {
            background: rgba(40, 57, 101, 0.6);
        }

        button.delete {
            width: 70%;
        }

        .error-message {
            color: red;
            margin-top: 2px;
        }

        .modal {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: rgba(40, 57, 101, 0.6);
            z-index: 3;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            text-align: center;
            color: #ffffff;
        }

        .modal p {
            font-size: 18px;
            margin-bottom: 20px;
        }

        button.modal-button {
            margin-top: 10px;
            padding: 10px 20px;
            background-color: rgba(40, 57, 101, 0.6);
            color: #ffffff;
            border: 1px solid #3498db;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }

        button.modal-button:hover {
            background-color: #3498db;
            color: black;
        }
    </style>
</head>

<body>
    <div class="background-container"></div>
    <div class="overlay"></div>
    <div class="container">
        <form action="ListeProjetsServlet" method="get">
            <label for="searchQuery" required>Recherche de Projet:</label>
            <input type="text" id="searchQuery" name="searchQuery" required placeholder="Nom du Projet">
<span>
            <button type="submit" >Rechercher</button>
             <button type="button" onclick="goBack()">Annuler</button></span>
        </form>
       <% if (request.getAttribute("searchResults") != null) { %>
    <% List<Projet> searchResults = (List<Projet>) request.getAttribute("searchResults"); %>
    <%-- Si un seul projet est trouvé, dirigez directement vers ModifierProjet.jsp avec les détails du projet --%>
    <% if (searchResults.size() == 1) { %>
        <% Projet projet = searchResults.get(0); %>
        <%-- Ajouter l'id du projet comme paramètre dans la requête --%>
        <% request.setAttribute("selectedProjectId", projet); %>
        <%-- Redirection vers ModifierProjet.jsp avec les détails du projet --%>
        <%
        RequestDispatcher dispatcher = request.getRequestDispatcher("modifierProjet.jsp");
        dispatcher.forward(request, response);
       System.out.println(projet.getChefDeProjet());
        
       
        %>
    <% } %>
<% } else { %>
    <%-- Aucun résultat, afficher le message d'erreur --%>
    <div class="error-message">
        <%= request.getAttribute("errorMessage") %>
    </div>
<% } %>
    </div>
</body>

</html>
