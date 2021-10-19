import ru.sber.filesystem.VFilesystem
import java.io.IOException
import java.net.ServerSocket
import java.io.PrintWriter
import java.net.Socket
import mu.KotlinLogging
import ru.sber.filesystem.VPath


/**
 * A basic and very limited implementation of a file server that responds to GET
 * requests from HTTP clients.
 */
class FileServer {

    /**
     * Main entrypoint for the basic file server.
     *
     * @param socket Provided socket to accept connections on.
     * @param fs     A proxy filesystem to serve files from. See the VFilesystem
     *               class for more detailed documentation of its usage.
     * @throws IOException If an I/O error is detected on the server. This
     *                     should be a fatal error, your file server
     *                     implementation is not expected to ever throw
     *                     IOExceptions during normal operation.
     */
    @Throws(IOException::class)
    fun run(socket: ServerSocket, fs: VFilesystem) {

        /**
         * Enter a spin loop for handling client requests to the provided
         * ServerSocket object.
         */
        socket.use {
            while (true) {
                LOG.info("Server started at ${socket.localPort}. Listening for client connections...")
                val clientSocket = it.accept()
                handle(clientSocket, fs)
            }
        }
    }

    private fun handle(socket: Socket, fs: VFilesystem) {
        LOG.info { "client connected:${socket.remoteSocketAddress}" }
        socket.use { s ->
            val reader = s.getInputStream().bufferedReader()
            val clientRequest = reader.readLine()
            LOG.info { "receive from ${socket.remoteSocketAddress} > clientRequest $clientRequest" }

            val writer = PrintWriter(s.getOutputStream())
            val serverResponse = getResponse(clientRequest, fs)
            writer.println(serverResponse)
            writer.flush()
            LOG.info { "send to ${socket.remoteSocketAddress} > $serverResponse" }
        }
    }

    private fun getResponse(clientRequest: String, fs: VFilesystem): String {
        val vPath = VPath(clientRequest.split(" ")[1])
        val file = fs.readFile(vPath)
        return if (file != null) {
            "HTTP/1.0 200 OK\r\n" +
                    "Server: FileServer\r\n" +
                    "\r\n" +
                    "$file\r\n"
        } else {
            "HTTP/1.0 404 Not Found\r\n\n" +
                    "Server: FileServer\r\n\n" +
                    "\r\n\n"
        }
    }

    companion object {
        val LOG = KotlinLogging.logger {}
    }
}