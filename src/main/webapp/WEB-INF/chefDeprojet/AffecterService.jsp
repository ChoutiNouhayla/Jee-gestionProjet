<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Models.Service" %>
<%@ page import="Models.Developpeur" %>
<%@ page import="Models.Projet" %>
<%@ page import="Models.Taches" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body {
            margin: auto;
            color: #ffffff;
            font: 600 16px/18px 'Open Sans', sans-serif;
            position: relative;
            padding: 20px;
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

        .custom-form {
            text-align: center;
            margin-top: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .custom-form label {
            display: block;
            margin-bottom: 5px;
        }

        .custom-form input[type="text"] {
            width: 50%; /* Adjust the width as needed */
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            background: rgba(255, 255, 255, 0.3);
            color: white;
            font-size: 18px;
        }

        .custom-form button {
            box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
            border: none;
            color: white;
            padding: 10px 20px;
            font-size: 18px;
            cursor: pointer;
            border-radius: 5px;
        }

        .custom-form button:hover {
            background: rgba(40, 57, 101, .8);
        }

        .service-list-container {
            text-align: center;
            margin: 20px auto;
            width: 60%; /* Adjust the width as needed */
        }

        .task-list-container {
            text-align: center;
            margin: 20px auto;
            width: 60%; /* Adjust the width as needed */
        }

        .service-list {
            list-style: none;
            padding: 0;
        }

        .service-item {
            cursor: pointer;
            margin: 10px;
            padding: 10px;
            border: 1px solid #ffffff;
            box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
            border-radius: 5px;
        }

        .service-item a {
            color: #ffffff;
            text-decoration: none;
        }

        .service-item a:hover {
            text-decoration: underline;
        }

        .task-list {
            list-style: none;
            padding: 0;
        }

        .task-item {
            margin: 10px;
            padding: 10px;
            border: 1px solid #ffffff;
            box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
            border-radius: 5px;
        }

        .task-item label {
            color: #ffffff;
            display: block;
            margin-bottom: 5px;
        }

        .task-item input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #f8f8f8;
            color: #333;
        }

        .task-item button {
            box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 18px;
            cursor: pointer;
            border-radius: 5px;
        }

        .task-item button:hover {
            background: rgba(40, 57, 101, .8);
        }

        .button {
            box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 25px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
        }

        .avancement-container {
            display: inline-block;
            vertical-align: top;
            margin-right: 10px; /* Adjust the margin as needed */
        }

        .avancement-container label {
            display: inline-block;
            margin-right: 5px; /* Adjust the margin as needed */
        }

        .avancement-container input {
            display: inline-block;
        }

        .toast {
            background-color: #4CAF50;
            color: white;
            padding: 16px;
            position: fixed;
            bottom: 15px;
            right: 15px;
            z-index: 1;
            border-radius: 5px;
            display: none; /* Initially hide the toast */
        }
       .developer-list {
            list-style: none;
            padding: 0;
        }

        .developer-item {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
            position: relative; /* Permet d'utiliser une position absolue dans les éléments enfants */
        }

        .developer-item a {
            margin-right: 10px;
            color: #333; /* Couleur du texte pour les liens */
            text-decoration: none;
            cursor: pointer;
        }

        .service-details {
            display: none;
            margin-top: 20px;
            margin-left:20px;
            position : center;
           
             width: 90%; /* Prend toute la largeur du parent */
            padding: 10px;
            border: 1px solid #333; /* Bordure autour de la div */
           box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
        }

        .input-group {
            margin-bottom: 10px;
        }

        .input-group label {
            display: block;
            margin-bottom: 5px;
        }

        .input-group input {
            width: 80%;
            padding: 5px;
            box-sizing: border-box;
        }

        .button-container button{
            font-size: 16px; /* Ajustez la taille du texte du bouton si nécessaire */
            padding: 10px 20px;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
        }
         .details-section {
        margin-top: 20px;
        padding: 20px;
         box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
        border: 1px solid #333;
        border-radius: 5px;
    }

    .details-section form {
        text-align: left; /* Ajustez l'alignement du texte si nécessaire */
    }

    hr {
        margin-top: 20px;
        margin-bottom: 20px;
        border: 2px solid #fff;
    }
    .input-group label {
    display: block;
    margin-bottom: 5px;
    color:white;
}

.input-group input,
.input-group textarea {
 font-size: 20px;
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
    background: rgba(255, 255, 255, 0.3); color:white;
    margin-bottom: 10px;
}

/* Style pour les boutons */
.button-container button {
    font-size: 16px;
    padding: 10px 20px;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    background: rgba(40, 57, 101, 0.6);
}

.button-container button:hover {
    background: rgba(40, 57, 101, 0.8);
}
   .error-message{
        background-color: red; /* Couleur de fond verte */
        color: #fff; /* Couleur du texte blanc */
        padding: 15px; /* Marge intérieure pour l'apparence */
        margin-bottom: 20px; /* Marge inférieure pour l'espacement avec le contenu suivant */
        border-radius: 5px; /* Coins arrondis */
    }


    </style>
    <title>Service</title>
   <script>
    function toggleServiceDetails(developerId) {
        var serviceDetails = document.getElementById('serviceDetails_' + developerId);
        serviceDetails.style.display = serviceDetails.style.display === 'none' ? 'block' : 'none';
    }
</script>

</head>

<body>

    <div class="background-container"></div>
    <div class="overlay"></div>
    <div class="container"><% String errorMessage = (String) request.getAttribute("errorHtml"); %>
    <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
        <div class="error-message">
            <%= errorMessage %>
        </div>
    <% } %>
        <form action="AffecterServiceChef" method="post" class="custom-form">
            <div class="form-group">
                <label for="searchProject">Rechercher un projet :</label>
                <input type="text" id="searchProject" name="projectName" required>
            </div>
            <div class="form-group">
                <button onclick="retour()" class="button">Retour</button>
                <button type="submit" class="button">Rechercher</button>
            </div>
        </form>
        <div class="service-list-container">
            <ul class="service-list">
                <%  List<Developpeur> developpeurId = (List<Developpeur>) request.getAttribute("developpeurId");
                   if (developpeurId != null) {
                       for (Developpeur service : developpeurId) { %>
                           <ol class="service-item">
                               <a href="javascript:void(0);" onclick="toggleServiceDetails('<%= service.getId() %>')"><strong>Développeur Détails</strong></a>
                             <br>  <label>Nom  :</label>
                               <%= service.getNom() %><br/>
                               <label>Prenom:</label>
                               <%= service.getPrenom() %><br/>
                           </ol>
                           <!-- Div des détails du service avec un ID dynamique -->
                           <div id="serviceDetails_<%= service.getId() %>" class="details-section" style="display: none;">
                                <h3>Détails du Service</h3>
                                <form action="votre-servlet" method="post">
                                    <div class="input-group">
                                        <label for="duree_<%= service.getId() %>">Durée (en jours):</label>
                                        <input type="text" id="duree_<%= service.getId() %>" name="duree" style="  background: rgba(255, 255, 255, 0.3); color:white" placeholder="duree">
                                    </div>

                                    <div class="input-group">
                                        <label for="description_<%= service.getId() %>">Description:</label>
                                        <textarea id="description_<%= service.getId() %>" name="description"placeholder="description"></textarea>
                                    </div>
                                  
                                     <div class="button-container">
                <button type="submit">Valider</button>
            </div>
                                </form>
                               
                                
                           </div>
                       <% }
                   } %>
            </ul>
        </div>
    </div>
    <script>
        function retour() {
            window.history.back();
        }
    </script>
</body>
</html>
