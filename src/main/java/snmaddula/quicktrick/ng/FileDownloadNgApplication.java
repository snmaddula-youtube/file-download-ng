package snmaddula.quicktrick.ng;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class FileDownloadNgApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileDownloadNgApplication.class, args);
	}

	@GetMapping("/download")
	public void downloadFile(String fileName, HttpServletResponse res) throws Exception {
		res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		res.getOutputStream().write(contentOf(fileName));
	}
	
	private byte[] contentOf(String fileName) throws Exception {
		return Files.readAllBytes( Paths.get(getClass().getClassLoader().getResource(fileName).toURI()));
	}
}
