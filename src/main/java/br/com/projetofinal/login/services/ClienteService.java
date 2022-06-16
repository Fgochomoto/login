package br.com.projetofinal.login.services;

//import br.com.projetofinal.login.TotpManager;
import br.com.projetofinal.login.entity.Cliente;
import br.com.projetofinal.login.repository.ClienteRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        ByteArrayOutputStream outputStream = null;
        BitMatrix bitMatrix;

        try {
            Random r = new Random();
            String novaSenha = String.format("%04d", r.nextInt(1001));
            bitMatrix = qrCodeWriter.encode(novaSenha, BarcodeFormat.QR_CODE, 50,50);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", FileSystems.getDefault().getPath("C:\\Users\\felip\\git\\login\\output\\QRcode.png"));

            InputStream iSteamReader = new FileInputStream("C:\\Users\\felip\\git\\login\\output\\QRcode.png");
            byte[] imageBytes = iSteamReader.readAllBytes();
            String base64 = Base64.getEncoder().encodeToString(imageBytes);
            System.out.println(base64);
            cliente.setSecretImageUri(base64);
            cliente.setNovaSenha(novaSenha);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listaCliente(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id){
        return clienteRepository.findById(id);
    }

    public void removerPorId(Long id){
        clienteRepository.deleteById(id);
    }

}
