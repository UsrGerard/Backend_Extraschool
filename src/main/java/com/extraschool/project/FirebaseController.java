package com.extraschool.project;


import com.extraschool.project.model.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@RestController
public class FirebaseController {

/*
	@RestController
	@RequestMapping("/profesores")
	public static class ProfesorController {
		Firestore db = FirestoreClient.getFirestore();

		@PostMapping
		public ResponseEntity<Profesor> crearUsuario(@RequestBody Profesor profesores) throws ExecutionException, InterruptedException {
			// Lógica para crear un usuario en la base de datos o hacer cualquier otra operación
			System.out.println("HA FUNCIONADO   " + profesores.toString());

			String nombre = profesores.getNombre();
			String apellido = profesores.getApellido();
			String email = profesores.getEmail();
			String contrasena = profesores.getContrasena();


			Profesor newUser = new Profesor(nombre, apellido, email, contrasena);
			ApiFuture<WriteResult> writeResult = db.collection("profesor").document().set(newUser);
			System.out.println("Update time : " + writeResult.get().getUpdateTime());


			return ResponseEntity.ok(profesores);
		}
	}


	@RestController
	@RequestMapping("/alumnos")
	public static class AlumnoController {
		Firestore db = FirestoreClient.getFirestore();

		@PostMapping
		public ResponseEntity<Alumnos> crearUsuario(@RequestBody Alumnos alumnos) throws ExecutionException, InterruptedException {
			// Lógica para crear un usuario en la base de datos o hacer cualquier otra operación

			String nombre = alumnos.getNombre();
			String apellido = alumnos.getApellido();
			String email = alumnos.getEmail();
			String contrasena = alumnos.getContrasena();


			Profesor newUser = new Profesor(nombre, apellido, email, contrasena);
			ApiFuture<WriteResult> writeResult = db.collection("alumnos").document().set(newUser);
			System.out.println("Update time : " + writeResult.get().getUpdateTime());

			return ResponseEntity.ok(alumnos);
		}
	}
*/


	@RestController
	@RequestMapping("/api")
	public static class apiControllerProfesor {
		Firestore db = FirestoreClient.getFirestore();

		@PostMapping("/profesores")
		public ResponseEntity<Profesor> handlePostRequest(@RequestBody Profesor profesores) throws ExecutionException, InterruptedException {

			// Verificar si el campo "contrasena" es nulo o vacío
			/*if (profesores.getContrasena() == null || profesores.getContrasena().isEmpty()) {
				// Generar una excepción o establecer un valor predeterminado
				throw new IllegalArgumentException("El campo 'contraseña' no puede ser nulo o vacío");
			}*/


			// Lógica para crear un usuario en la base de datos o hacer cualquier otra operación
			String id = profesores.getId();
			String nombre = profesores.getNombre();
			String apellido = profesores.getApellido();
			String email = profesores.getEmail();
			String contrasena = profesores.getContrasena();
			String materia = profesores.getMateria();
            String imagen = profesores.getImagen();


			Profesor newUser = new Profesor(id, nombre, apellido, email, contrasena, materia, imagen);
			ApiFuture<WriteResult> writeResult = db.collection("profesor").document().set(newUser);
			System.out.println("Update time : " + writeResult.get().getUpdateTime());


			System.out.println(profesores);
			return ResponseEntity.ok(profesores);
		}

	}


	@RestController
	@RequestMapping("/api")
	public static class apiControllerAlumnos {
		Firestore db = FirestoreClient.getFirestore();

		@PostMapping("/alumnos")
		public ResponseEntity<Alumnos> handlePostRequest(@RequestBody Alumnos alumnos) throws ExecutionException, InterruptedException {
			// Lógica para crear un usuario en la base de datos o hacer cualquier otra operación

			String nombre = alumnos.getNombre();
			String apellido = alumnos.getApellido();
			String email = alumnos.getEmail();
			String contrasena = alumnos.getContrasena();


			Alumnos newUser = new Alumnos(nombre, apellido, email, contrasena);
			ApiFuture<WriteResult> writeResult = db.collection("alumnos").document().set(newUser);
			System.out.println("Update time : " + writeResult.get().getUpdateTime());


			return ResponseEntity.ok(alumnos);
		}
	}


	@RestController
	@RequestMapping("/api")
	public static class AlumnosController {

		Firestore db = FirestoreClient.getFirestore();

		@PostMapping("/loginAlumnos")
		public ResponseEntity<LoginAlumno> handlePostRequest(@RequestBody LoginAlumno loginAlumnos) throws ExecutionException, InterruptedException {
			ApiFuture<QuerySnapshot> query = db.collection("alumnos").get();
			List<QueryDocumentSnapshot> documents = query.get().getDocuments();

			String email = loginAlumnos.getEmail();
			String contrasena = loginAlumnos.getContrasena();

			for (QueryDocumentSnapshot document : documents) {
				LoginAlumno alumnos1 = document.toObject(LoginAlumno.class);

				if (alumnos1.getEmail().equals(email) && alumnos1.getContrasena().equals(contrasena)) {
					// Las credenciales coinciden
					System.out.println("Las credenciales son correctas.");
					// Aquí podrías retornar una respuesta o hacer alguna otra acción
					return ResponseEntity.ok(loginAlumnos);
				} else {
					System.out.println("no son iguales");
				}
			}

			return ResponseEntity.notFound().build();
		}
	}

	@RestController
	@RequestMapping("/api")
	public static class ProfesoresController {
		Firestore db = FirestoreClient.getFirestore();

		@PostMapping("/loginProfesores")
		public ResponseEntity<LoginProfesor> handlePostRequest(@RequestBody LoginProfesor loginProfesor) throws ExecutionException, InterruptedException {
			ApiFuture<QuerySnapshot> query = db.collection("profesor").get();
			List<QueryDocumentSnapshot> documents = query.get().getDocuments();

			String email = loginProfesor.getEmail();
			String contrasena = loginProfesor.getContrasena();

			for (QueryDocumentSnapshot document : documents) {
				LoginProfesor profesores1 = document.toObject(LoginProfesor.class);

				if (profesores1.getEmail().equals(email) && profesores1.getContrasena().equals(contrasena)) {
					// Las credenciales coinciden
					System.out.println("Las credenciales son correctas.");
					// Aquí podrías retornar una respuesta o hacer alguna otra acción
					return ResponseEntity.ok(loginProfesor);
				} else {
					System.out.println("no son iguales");
				}
			}

			return ResponseEntity.notFound().build();
		}
	}


	@RestController
	@RequestMapping("/api")
	public static class GetAlumnosController {

		@GetMapping("/getAlumnos")
		public ResponseEntity<List<Alumnos>> getAlumnos() throws ExecutionException, InterruptedException {
			// Obtiene una instancia de Firestore
			Firestore db = FirestoreClient.getFirestore();

			// Obtiene todos los documentos de la colección "alumnos"
			ApiFuture<QuerySnapshot> query = db.collection("alumnos").get();
			List<QueryDocumentSnapshot> documents = query.get().getDocuments();

			// Convierte los documentos de Firebase en objetos Alumno
			List<Alumnos> alumnos = new ArrayList<>();
			for (QueryDocumentSnapshot document : documents) {
				Alumnos alumno = document.toObject(Alumnos.class);
				alumnos.add(alumno);

			}

			// Retorna los objetos Alumno en formato JSON
			return ResponseEntity.ok(alumnos);
		}
	}

	@RestController
	@RequestMapping("/api")
	public static class GetProfesoresController {

		@GetMapping("/getProfesores")
		public ResponseEntity<List<Profesor>> getProfesores() throws ExecutionException, InterruptedException {
			// Obtiene una instancia de Firestore
			Firestore db = FirestoreClient.getFirestore();

			// Obtiene todos los documentos de la colección "alumnos"
			ApiFuture<QuerySnapshot> query = db.collection("profesor").get();
			List<QueryDocumentSnapshot> documents = query.get().getDocuments();

			// Convierte los documentos de Firebase en objetos Alumno
			List<Profesor> profesors = new ArrayList<>();
			for (QueryDocumentSnapshot document : documents) {
				Profesor profesor = document.toObject(Profesor.class);
				profesors.add(profesor);

			}

			// Retorna los objetos Alumno en formato JSON
			return ResponseEntity.ok(profesors);
		}
	}

	@RestController
	@RequestMapping("/api")
	public static class GetProfesor {

		@PostMapping("/getProfesor") ///Funciona, muestra primer profesor
		public ResponseEntity<Profesor> getProfesor() throws ExecutionException, InterruptedException {
			Firestore db = FirestoreClient.getFirestore();

			ApiFuture<QuerySnapshot> query = db.collection("profesor").get();
			List<QueryDocumentSnapshot> documents = query.get().getDocuments();

			if (!documents.isEmpty()) {
				QueryDocumentSnapshot document = documents.get(0);
				Profesor profesor = document.toObject(Profesor.class);

				return ResponseEntity.ok(profesor);
			} else {
				return ResponseEntity.noContent().build();
			}
		}
	}


	/*@RestController
	@RequestMapping("/api")
	public static class GetPerfilProfesorController {

		@GetMapping("/getPerfil/{id}")
		public ResponseEntity<Profesor> getPerfilProfesor(@PathVariable String id) throws InterruptedException, ExecutionException {
			Firestore db = FirestoreClient.getFirestore();

			DocumentReference docRef = db.collection("profesor").document(id);
			ApiFuture<DocumentSnapshot> future = docRef.get();
			DocumentSnapshot document = future.get();

			if (document.exists()) {
				Profesor profesor = document.toObject(Profesor.class);
				return ResponseEntity.ok(profesor);
			} else {
				return ResponseEntity.notFound().build();
			}
		}
	}*/

	@RestController
	@RequestMapping("/api")
	public static class GetProfesorController {

		@GetMapping("/getPerfilProfesor/{id}")
		public ResponseEntity<Profesor> getPerfilProfesor(@PathVariable String id) throws InterruptedException, ExecutionException {
			if (id != null) {
				Firestore db = FirestoreClient.getFirestore();
				CollectionReference profesorRef = db.collection("profesor");
				Query query = profesorRef.whereEqualTo("id", id);
				ApiFuture<QuerySnapshot> querySnapshot = query.get();
				List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

				if (!documents.isEmpty()) {
					Profesor profesor = documents.get(0).toObject(Profesor.class);
					return ResponseEntity.ok(profesor);
				}
			}

			return ResponseEntity.notFound().build();
		}


	}

	@RestController
	@RequestMapping("/api")
	public static class GetProfesorEmailController {

		@GetMapping("/getPerfilProfesorEmail/{email}")
		public ResponseEntity<Profesor> getPerfilProfesorEmail(@PathVariable String email) throws InterruptedException, ExecutionException {
			if (email != null) {
				Firestore db = FirestoreClient.getFirestore();
				CollectionReference profesorRef = db.collection("profesor");
				Query query = profesorRef.whereEqualTo("email", email);
				ApiFuture<QuerySnapshot> querySnapshot = query.get();
				List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

				if (!documents.isEmpty()) {
					Profesor profesor = documents.get(0).toObject(Profesor.class);
					return ResponseEntity.ok(profesor);
				}
			}

			return ResponseEntity.notFound().build();
		}


	}





	@RestController
	@RequestMapping("/api")
	public static class UpdateProfesorController {

		@PutMapping("/updatePerfilProfesor/{id}")
		public ResponseEntity<String> updatePerfilProfesor(@PathVariable String id, @RequestBody Profesor profesorActualizado) {
			if (id != null) {
				Firestore db = FirestoreClient.getFirestore();
				CollectionReference profesorRef = db.collection("profesor");

				Query query = profesorRef.whereEqualTo("id", id);
				ApiFuture<QuerySnapshot> querySnapshot = query.get();

				try {
					List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

					if (!documents.isEmpty()) {
						for (QueryDocumentSnapshot document : documents) {
							document.getReference().set(profesorActualizado);
						}
						return ResponseEntity.ok("Profesor actualizado correctamente.");
					} else {
						return ResponseEntity.notFound().build();
					}
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
			} else {
				return ResponseEntity.badRequest().build();
			}
		}
	}


	/* @PutMapping(value = "cFotoEquipo", produces =  MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> cambiarFoto(@RequestBody EquipoDto  aEquipo) {
		service.cFoto(aEquipo.getId(),aEquipo.getFoto());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<String>("{\"Respuesta\": \"La Foto ha sido cambiado\"}", httpHeaders,
				HttpStatus.OK);
	} */







}