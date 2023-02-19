package io.github.pedrofraca

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import org.eclipse.microprofile.metrics.MetricUnits
import org.eclipse.microprofile.metrics.annotation.Counted
import org.eclipse.microprofile.metrics.annotation.Timed
import java.io.ByteArrayOutputStream
import java.util.*
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/generate")
class GeneratorResource {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Counted(description = "How many qrs get generated", absolute = true, name = "countQR")
    @Timed(name = "qr_generation", description = "A measure of how long it takes to generate a QR", unit = MetricUnits.MILLISECONDS)
    fun generate(request : GenerateQRRequest)  : String {

        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(request.data, BarcodeFormat.QR_CODE,
            request.width?:GenerateQRRequest.MIN_SIZE,
            request.height?:GenerateQRRequest.MIN_SIZE)

        val bos = ByteArrayOutputStream()
        MatrixToImageWriter.writeToStream(bitMatrix, "png", bos)
        bos.close()

        return Base64.getEncoder().encodeToString(bos.toByteArray())
    }
}