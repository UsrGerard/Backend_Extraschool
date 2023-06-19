package com.extraschool.project;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProjectApplication {

	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccount.json");
		@SuppressWarnings("deprecation")
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();
		FirebaseApp.initializeApp(options);


		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	public TomcatServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
			}
		};
		tomcat.addConnectorCustomizers(connector -> {
			connector.setMaxPostSize(100000000); // ajusta el tamaño máximo de las peticiones POST
		});
		return tomcat;
	}


}
