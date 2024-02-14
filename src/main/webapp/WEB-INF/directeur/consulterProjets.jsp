<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="Models.Projet" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html lang="en">

<head>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Projets</title>
    <style>
        body {
            margin: auto;
            color: #ffffff;
            font: 600 16px/18px 'Open Sans', sans-serif;
            position: relative;
            overflow-x: hidden;
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
            margin-top: 20px;
            z-index: 2;
            width: 100%;
        }

        .project-table {
            width: 80%;
            margin: auto;
            margin-top: 10px;
            border-radius: 30px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: rgba(40, 57, 101, 0.6);
            color: white;
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
    </style>
</head>

<body>
    <div class="background-container"></div>
  
    <div class="container">
   
        <div class="project-table">
            <table>
                <thead>
                    <tr>
                        <th>Nom du Projet</th>
                        <th>Description</th>
                        <th>Client</th>
                        <th>Date de Début</th>
                        <th>Date de Livraison</th>
                        <th>Jours de Développement</th>
                      
                    </tr>
                </thead>
                <tbody id="projectList">
 <% 
                List<Projet> projets = (List<Projet>) request.getAttribute("projets");

                for (Projet projet : projets) {
                    %>
                    <tr>
                        <td><%= projet.getNomProjet() %></td>
                        <td><%= projet.getDescription() %></td>
                        <td><%= projet.getClient() %></td>
                        <td><%= new SimpleDateFormat("yyyy-MM-dd").format(projet.getDateDebut()) %></td>
                        <td><%= new SimpleDateFormat("yyyy-MM-dd").format(projet.getDateLivraison()) %></td>
                        <td><%= projet.getJoursDeveloppement() %></td>
                       
        
                    </tr>
                    <%
                }
            %>
                </tbody>
            </table>
          
              
        </div>
          <button onclick="retour()" class="button">Retour</button>
    </div>

   
    <script>
   
    function retour() {
        // Retourne à la page précédente dans l'historique du navigateur
        console.log("erreur")
        window.history.back();
    }
</script>

 
    
  
</body>


</html>
