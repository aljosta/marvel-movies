# Marvel Comics
Esta es una aplicación que permite la visualizar los comics mediante listado de resultados, visualización del detalle y opción de ver los comics favoritos. 

## Funcionalidades
La aplicación contiene las siguiente características:

* Pantalla inicial que contiene lista de resultados obtenidos de la API y menú de navegación inferior.
* Pantalla de detalle con opción para agregar o remover el comic de los favoritos.
* Pantalla con el listado de los comics favoritos indicados por el usuario.

## Guía de implementación

### API
La consulta de comics y el detalle del mismo es realizada mediante la API de Marvel utilizando la librería de Retrofit.

### Database
Guardado local de la información de los comics con el dato de favorito por medio de la librería de Room.

### Arquitectura
Basada en la guía de arquitectura de Android y arquitectura limpia (Clean Architecture) que permite tener el código desacoplado y mantenible. La capas se agrupan por funcionalidades de la aplicación (listado de comics, detalle del comic y listado de favoritos).

###
* UI: ComicListView, ComicView, FavoriteListView, ComicListViewModel, ComicViewModel, FavoriteListViewModel
* Domain: GetComicByIdUseCase, GetComicsUseCase, SetFavoriteComicUseCase, GetFavoriteComicsUseCase
* Data: ComicDataRepository, ComicRemoteDataSource, ComicPagingSource, ComicLocalDataSource


### Inyección de dependencias
Se utiliza hilt para la inserción de dependencias y evitar acoplamiento dentro de las clases del proyecto, esto permite centralizar la inicialización de dependencias en módulos donde pueden ser facilmente reemplazadas por otras implementaciones.
