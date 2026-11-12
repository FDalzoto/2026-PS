import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImagemLivro {

    public static String salvar(
            Path origem, int idLivro)
            throws IOException {

        BufferedImage imagem =
            ImageIO.read(origem.toFile());

        if (imagem == null) {
            throw new IOException(
                "Arquivo de imagem inválido."
            );
        }

        if (Files.size(origem) > 5 * 1024 * 1024) {
            throw new IOException(
                "A imagem deve ter até 5 MB."
            );
        }

        String extensao = "png";

        String nome = idLivro + "-" +
            UUID.randomUUID() + "." + extensao;

        Path pasta = Path.of(
            "imagens", "livros"
        );

        Files.createDirectories(pasta);

        Path destino = pasta.resolve(nome);

        // Regrava a imagem em um formato conhecido.
        if (!ImageIO.write(
                imagem, "png", destino.toFile())) {
            throw new IOException(
                "Não foi possível salvar a imagem."
            );
        }

        return destino.toString()
            .replace('\\', '/');
    }
}