package br.com.devsrsouza.kotlin.docker.utils

import okio.ByteString.Companion.decodeHex
import okio.ByteString.Companion.encodeUtf8
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


internal class UnixSocket : Socket() {
    private lateinit var socket: AFUNIXSocket
    @Throws(IOException::class)
    override fun connect(endpoint: SocketAddress, timeout: Int) {
        val address = (endpoint as InetSocketAddress).address
        val socketPath = decodeHostname(address)
        val socketFile = File(socketPath)
        socket = AFUNIXSocket.newInstance()
        socket.connect(AFUNIXSocketAddress(socketFile), timeout)
        socket.setSoTimeout(timeout)
    }

    @Throws(IOException::class)
    override fun bind(bindpoint: SocketAddress?) {
        socket.bind(bindpoint)
    }

    override fun isConnected(): Boolean {
        return socket.isConnected()
    }

    @Throws(IOException::class)
    override fun getOutputStream(): OutputStream {
        return socket.getOutputStream()
    }

    @Throws(IOException::class)
    override fun getInputStream(): InputStream {
        return socket.getInputStream()
    }

    private object Encoder {
        fun encode(text: String): String {
            return text.encodeUtf8().hex()
        }

        fun decode(hex: String): String {
            return hex.decodeHex().utf8()
        }
    }

    companion object {
        fun encodeHostname(path: String): String {
            return Encoder.encode(path) + ".socket"
        }

        fun decodeHostname(address: InetAddress): String {
            val hostName = address.hostName
            return Encoder.decode(hostName.substring(0, hostName.indexOf(".socket")))
        }
    }
}