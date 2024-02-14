<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="Models.User"%>
<%@ page import="java.util.List"%>

<%@ page import="Servlet.UserList"%>
<%
    // Utiliser le service pour obtenir la liste des chefs de projet
    UserList userService = new UserList();
    List<User> chefsDeProjet = userService.getChefsDeProjet();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter Projet</title>
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
             background: url(prj.jpeg)  center;
           
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
            width: 100%; /* Formulaire occupe la largeur complète */
            max-width: 500px; /* Ajustez la largeur maximale selon vos besoins */
            margin: auto; /* Centrage du formulaire */
        }

        label {
            margin-top: 10px;
            font-size: 18px;
            color: #ffffff;
            text-align: left; /* Alignement du texte à gauche */
            width: 100%; /* Largeur complète du texte */
        }

        input, select {
            width: 100%; /* Champs de formulaire occupent la largeur complète */
            padding: 10px;
            margin: 10px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            background: rgba(255, 255, 255, 0.3); /* Fond transparent */
            color: #ffffff; /* Couleur du texte en blanc */
        }

        input::placeholder,
        select::placeholder {
            color: #ffffff; /* Couleur du texte en blanc */
        }

        button {
            font-size: 20px; /* Taille de police plus petite */
            padding: 5px 60px; /* Padding réduit */
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background: rgba(40, 57, 101, 0.6);
            margin-top: 5px;
            width: 48%; /* Bouton occupe la largeur complète */
        }

        .error-message {
            color: red;
            margin-top: 2px;
        }
    </style>
    <script>
        // Fonction pour vérifier la validité des dates et calculer la durée du projet
        function validateAndCalculate() {
            var startDate = new Date(document.getElementById("dateDebut").value);
            var endDate = new Date(document.getElementById("dateLivraison").value);
            var errorContainer = document.getElementById("errorContainer");
            var today = new Date();

            if (startDate < today || endDate < today) {
                errorContainer.innerHTML = "Les dates ne peuvent pas être antérieures à aujourd'hui.";
                return false;
            }

            if (startDate > endDate) {
                errorContainer.innerHTML = "La date de livraison ne peut pas être antérieure à la date de début.";
                return false;
            }

            return true;
        }

        function goBack() {
            window.history.back();
        }
    </script>
</head>
<body>
<%
    if (request.getAttribute("successHtml") != null) {
        out.print(request.getAttribute("successHtml"));
    }
%>

<% String errorMessage = (String)session.getAttribute("erreurAjoutProjet"); %>

    <%-- Afficher le message d'erreur s'il existe --%>
 
    <div class="background-container"></div>
    <div class="overlay"></div>
    <div class="container">
        <form  method="post"  action="${pageContext.request.contextPath}/GestionProjetServlet" onsubmit="return validateAndCalculate();">
            <div id="errorContainer" class="error-message" style="margin-top: 5px;"></div>
  <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
        <div style="color: red;">
            <%= errorMessage %>
        </div>
    <% } %>
            <label for="nomProjet">Nom du Projet:</label>
            <input type="text" id="nomProjet" name="nomProjet" required placeholder="Nom du Projet">

            <label for="descriptionProjet">Description du Projet:</label>
            <input type="text" id="descriptionProjet" name="descriptionProjet" required placeholder="Description du Projet">

            <label for="clientProjet">Client du Projet:</label>
            <input type="text" id="clientProjet" name="clientProjet" required placeholder="Client du Projet">

            <label for="dateDebut">Date de Début:</label>
            <input type="date" id="dateDebut" name="dateDebut" required placeholder="Date de Début" oninput="updateDays()">

            <label for="dateLivraison">Date de Livraison:</label>
            <input type="date" id="dateLivraison" name="dateLivraison" required placeholder="Date de Livraison" oninput="updateDays()">

            <label for="joursDeveloppement">Nombre de Jours de Développement:</label>
            <input type="number" id="joursDeveloppement" name="joursDeveloppement" required placeholder="Nombre de Jours de Développement" readonly>

            <label for="chefProjet">Chef de Projet:</label> 
            <select
				id="chefProjet" name="chefProjet" required>
				<%
        for (User chef : chefsDeProjet) {
    %>
				<option value="<%= chef.getEmail() %>" style="color: black;"><%= chef.getEmail() %></option>
				<!-- Assurez-vous d'utiliser chef.getName() ou la méthode appropriée pour obtenir le nom de l'utilisateur -->
				<%
        }
    %>
			</select>

            <div style="display: flex; justify-content: space-between;">
                <button type="button" onclick="goBack()">Annuler</button>
                <button type="submit" >Ajouter Projet</button>
            </div>
        </form>
    </div>

    <script>
        function updateDays() {
            var startDate = new Date(document.getElementById("dateDebut").value);
            var endDate = new Date(document.getElementById("dateLivraison").value);
            var errorContainer = document.getElementById("errorContainer");
            var today = new Date();

            if (startDate < today || endDate < today) {
                errorContainer.innerHTML = "Les dates ne peuvent pas être antérieures à aujourd'hui.";
                return;
            }

            if (startDate > endDate) {
                errorContainer.innerHTML = "La date de livraison ne peut pas être antérieure à la date de début.";
                return;
            }

            var timeDiff = Math.abs(endDate.getTime() - startDate.getTime());
            var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

            document.getElementById("joursDeveloppement").value = diffDays;
            errorContainer.innerHTML = ""; // Efface les messages d'erreur
        }
    </script>
</body>
</html>
