# CV Analyzer – Projet de Mémoire

Ce projet s'inscrit dans le cadre de mon mémoire de fin d'études. Il a pour objectif de concevoir une solution d’analyse automatique de CV en combinant la **vision par ordinateur**, le **traitement du langage naturel (NLP)** et une **application backend avec Spring Boot**.

## Objectifs du projet

- Numériser des CV au format PDF ou image à l’aide de l’OCR
- Extraire automatiquement les informations importantes :  
  - Nom, prénom, email, téléphone  
  - Expériences professionnelles  
  - Compétences  
  - Formations  
- Anonymiser les données personnelles pour garantir la confidentialité
- Restituer un CV "nettoyé", structuré et prêt pour l’analyse RH
- Cartographier les profils pour un meilleur matching avec les besoins du marché

## Technologies utilisées

- **OCR** : Tesseract / EasyOCR
- **NLP** : spaCy / Transformers
- **Backend** : Spring Boot (Java)
- **Frontend (optionnel)** : à compléter si tu as une interface
- **Autres outils** : Pandas, Scikit-learn, Postman (tests API), etc.

## Architecture générale

1. Upload du CV via l’API
2. Traitement OCR pour extraire le texte brut
3. Extraction des entités clés via NLP
4. Anonymisation du texte
5. Restitution du contenu structuré

Plusieurs interface à disposition comme : l'ajout de candidat, consultant, recherche de profils etc

## Aperçu
<img width="960" height="540" alt="image" src="https://github.com/user-attachments/assets/ab231d50-44e9-4a20-b710-88724bfcc54c" />
<img width="960" height="540" alt="image" src="https://github.com/user-attachments/assets/a8fcc784-199d-4a1f-96c0-fa54a9e078b6" />
<img width="960" height="540" alt="image" src="https://github.com/user-attachments/assets/0b81686f-d52c-4988-b7f0-625b2563afa3" />
<img width="960" height="540" alt="image" src="https://github.com/user-attachments/assets/46fd5a68-4ecf-4aad-badc-0c78bf2bbb91" />
<img width="1920" height="1080" alt="Capture d’écran 2025-08-01 114647" src="https://github.com/user-attachments/assets/d7dc1878-dabd-43fc-933c-d3211dab5d7f" />
<img width="960" height="540" alt="image" src="https://github.com/user-attachments/assets/30faa464-0ee8-497e-a1c6-7f10dcd2b58b" />







