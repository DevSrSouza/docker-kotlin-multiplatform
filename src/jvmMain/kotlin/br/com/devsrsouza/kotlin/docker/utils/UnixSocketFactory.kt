package br.com.devsrsouza.kotlin.docker.utils

import okhttp3.Dns
import okhttp3.HttpUrl
import org.newsclub.net.unix.AFUNIXSocket
import java.io.IOException
import java.net.InetAddress
import java.net.Socket
import java.net.UnknownHostException
import javax.net.SocketFactory


internal class UnixSocketFactory : SocketFactory(), Dns {
    companion object {
        fun urlForUnixSocketPath(unixSocketPath: String, path: String): HttpUrl {
            return HttpUrl.Builder()
                .scheme("http")
                .host(UnixSocket.encodeHostname(unixSocketPath))
                .addPathSegment(path)
                .build()
        }
    }

    @Throws(UnknownHostException::class)
    override fun lookup(hostname: String): List<InetAddress> {
        return if (hostname.endsWith(".socket")) listOf(
            InetAddress.getByAddress(
                hostname,
                byteArrayOf(0, 0, 0, 0)
            )
        ) else Dns.SYSTEM.lookup(hostname)
    }

    @Throws(IOException::class)
    override fun createSocket(): Socket {
        return UnixSocket()
    }

    @Throws(IOException::class)
    override fun createSocket(s: String, i: Int): Socket {
        throw UnsupportedOperationException()
    }

    @Throws(IOException::class)
    override fun createSocket(s: String, i: Int, inetAddress: InetAddress, i1: Int): Socket {
        throw UnsupportedOperationException()
    }

    @Throws(IOException::class)
    override fun createSocket(inetAddress: InetAddress, i: Int): Socket {
        throw UnsupportedOperationException()
    }

    @Throws(IOException::class)
    override fun createSocket(inetAddress: InetAddress, i: Int, inetAddress1: InetAddress, i1: Int): Socket {
        throw UnsupportedOperationException()
    }

    init {
        if (!AFUNIXSocket.isSupported()) {
            throw UnsupportedOperationException("AFUNIXSocket.isSupported() == false")
        }
    }
}