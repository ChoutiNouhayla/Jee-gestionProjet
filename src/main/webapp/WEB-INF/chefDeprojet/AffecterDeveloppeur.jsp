<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Models.Developpeur" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Développeurs</title>
    <style>
        body {
    margin: 0;
    padding: 0;
    font-family: 'Open Sans', sans-serif;
    background: #f2f2f2;
    color: #333;
}

.overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
    background: url(prj.jpeg);
    filter: blur(3px);
}

.container {
    text-align: center;
    margin: 100px auto;
    padding: 20px;
    background: #fff;
    border-radius: 8px;
      box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0
		rgba(0, 0, 0, .19);
			background: rgba(10, 57, 101, .6);
			 width: 80%;
}

h2 {
    color: #fff;
    font-size: 36px;
}

.developer-list {
    list-style: none;
    padding: 0;
}

.developer-item {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
    padding: 10px;
    color: #fff;
    border: 1px solid color: #fff;
    border-radius: 5px;
    box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0
		rgba(0, 0, 0, .19);
		background: rgba(40, 57, 101, .6);
		
}

.developer-checkbox {
    margin-right: 10px;
}

.button-container {
    margin-top: 20px;
}

button {
    font-size: 18px;
    padding: 15px 30px;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
   background: rgba(40, 57, 101, .6);
}

button:hover {
    background: rgba(10, 57, 101, .6);
}


    </style>
</head>
<body>
    <div class="overlay"></div>
    <div class="container">
        <h2>Liste des Développeurs</h2>

        <form action="AffecterDevlopeur" method="post"> <!-- Assurez-vous de spécifier la bonne action -->
            <ul class="developer-list">
                <!-- Utilisez une boucle forEach pour parcourir la liste des développeurs -->
                <input type="hidden" name="projetId" value="<%= request.getAttribute("projetId") %>">
                <% List<Developpeur> developpeurs = (List<Developpeur>) request.getAttribute("Devellopeur"); %>
                <% if (developpeurs != null && !developpeurs.isEmpty()) { %>
                    <% for (Developpeur developpeur : developpeurs) { %>
                        <li class="developer-item">
                            <!-- Utilisez un champ de formulaire de type checkbox pour chaque développeur -->
                            <input type="checkbox" class="developer-checkbox" name="selectedDevelopers" value="<%= developpeur.getId() %>">
                            <%= developpeur.getNom()  %> <%=  developpeur.getPrenom()  %><!-- Affichez les détails du développeur -->
                            <!-- Ajoutez d'autres champs ici selon vos besoins -->
                        </li>
                    <% } %>
                <% } else { %>
                    <li style=" color: #fff;">Aucun développeur trouvé</li>
                <% } %>
            </ul>

            <div class="button-container">
                <button type="submit">Valider</button>
            </div>
        </form>
    </div>
</body>
</html>
