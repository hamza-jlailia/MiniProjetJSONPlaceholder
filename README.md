# Mini-projet REST API – JSONPlaceholder

## Fonctionnalités
- Liste des posts (LazyColumn + états Loading/Error/Empty)
- Détail d'un post + ses commentaires
- Création et modification de post (formulaire avec validation)
- Suppression (simulée)
- Navigation avec arguments (NavHost + navArgument)
- Architecture MVVM + Repository + Retrofit + StateFlow
- Gestion Snackbar pour feedback utilisateur

## Technologies
- Jetpack Compose
- Material 3
- Navigation Compose
- Retrofit 2 + Gson
- Coroutines + StateFlow
- MVVM + Repository pattern

## API testée
Base : https://jsonplaceholder.typicode.com
Endpoints utilisés :
- GET /posts
- GET /posts/{id}
- GET /posts/{id}/comments
- POST /posts
- PATCH /posts/{id}
- DELETE /posts/{id}


## Lancement
1. Cloner le projet
2. Ouvrir avec Android Studio
3. Build & Run (émulateur ou appareil)
