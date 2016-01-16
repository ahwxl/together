package com.bplow.netconn.base.security;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-myclient.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class Base64EncodeTest {
	
	@Autowired
	Base64Encode base64Encode;
	
	//@Ignore
	@Test
	public void encode() throws IOException{
		
		//base64Encode.encodeString("abc");
		//base64Encode.decodeStrNoToFile("PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiID8+PHJlc3BvbnNlPjxoZWFkPg0KPHJlc3BvbnNlQ29kZT5PSzwvcmVzcG9uc2VDb2RlPg0KPHJlc3BvbnNlSW5mbz5PSzwvcmVzcG9uc2VJbmZvPg0KPC9oZWFkPg0KPGJvZHk+DQo8bWVyY2hhbnRObz44MDAwMTAxMDAwMDgyMzwvbWVyY2hhbnRObz4NCjx0cmFjZU5vPjIwMTUwOTExMDAwMDAwNTMxNDg8L3RyYWNlTm8+DQo8dHJhbkNvZGU+MDE8L3RyYW5Db2RlPg0KPGN1cnJlbmN5PjE1NjwvY3VycmVuY3k+DQo8YW1vdW50PjM8L2Ftb3VudD4NCjx0cmFuU3RhdHVzPjE8L3RyYW5TdGF0dXM+DQo8cmVjdlRpbWU+MjAyODAzMDcxMzM3MDQ8L3JlY3ZUaW1lPg0KPHRyYW5UaW1lPjIwMjgwMzA3MTMzNzA2PC90cmFuVGltZT4NCjwvYm9keT4NCjwvcmVzcG9uc2U+");
		//base64Encode.decodeString("MIIKUgIBAzCCChgGCSqGSIb3DQEHAaCCCgkEggoFMIIKATCCBt8GCSqGSIb3DQEHBqCCBtAwggbMAgEAMIIGxQYJKoZIhvcNAQcBMBwGCiqGSIb3DQEMAQYwDgQIyJa205T7Xt4CAggAgIIGmKo480mnzMAbZsryWT5KG2HLAslAE6zNFe/KDjlTNYPx2++HE0zhkqx/ydDlSpOE0M+UyLHGVcMH2Reu2L8ohBxLnPa4Ekv2oQq2W5Ch4k5LEJJt6X3QymXtaVBPmDoYiWhJpQd6+K750EN1p9t8a8XPjEYjjxRUS5jFqE/Z8QqOIV9sfJGixsKjIFzDwKSyq9m8r2sz+kZj4L8lPcMGWCQDUxuZ0i+JBmyYgVopSmNXhy19Z/b0T+SmthP8pMrmQ/GanNp7Xr90g3MPQ7n31zRpTLxZN/Sp/GNksehnFQIa7IcrY46eCW78x9hf8eUQOEGmXD6CCiyzjlOggyozRS6bEHFbLKBelMqL1nmeM/z2qC6cwH+ngo0yFFYGG+aY/zpCk9ds9CUvdBJRjSIdBd5dLG5YbfI7R06SuBMXJbcsVTBtxeE9At4hwUqFM8EzeGDC65o/CeW6sKUSSAKofjyV02h00hd78K+XfQekPUTzgDUFlNcC7gM3mQfvQkrfGvi4Nx4KTr6QZPGOQfTl9B6OiLe/8Euec4jHs9DGlTGHYNJZFFkutJstMpP1ItYKHgkB2aXCzX+04fgkaQ8ykpQ717htbL0t4SgeJxMWJxxFN5RdLI6DXPYMmAayeMhnH65IhE1drVygF53LS+BCxMZy6B9FyGBOw5ad1oCGrPkkhyqObz0RYYfqh9IE7ocUOERoPVsiCbHwmu3qO8WpTFKLLPTGX1qOlewIZmvCSAqh0QmxUi5SJhxpwCziIIVXm150Cp9UCsuIz79hSQ5zuCa5NtXyOZPr4g6AS5ZA7NHGY4joAX1SCXyW8OROYapZX37qdysgifY2xVI4ABDvtq5NzPo51l4f9TiXC5zzlqvk5i2l86YUT35CERWFBvpzNRGA4X869l+egR/dsACTCiVaZrHk04DUiPQoGaINyAIxpLKRuA3bPE3nl8fYE+SKao4pOwDiwFxCU2tlKPvLMdgXD/xAEmNgadWsfixTMRXqUxgBtCZfu3yDh8UXUzXP+paFPCEWM6KtTggQhr1MK5woJvUsaeCucspYHbKj8d4n96aVv51DAHMITIs60GQxTR2Lh77khAQwK68hCwqydekzHF8hmwrQCCpbB2w+Fe8u/10q27VPrLmxKux1J7HxocXRFnHnOMJMUQCaE4piTe6n+W8NpiiDgIAz+5fiGzLuCDx4rNQepCWQrbtHraRSJk6AplPnaABGeUsW/yQiv+BzQDkgf+tU8WwOfntfjEzex4e98ghiHBLUv9PFwLVO8TuoAa9Wip5Ag3LI3KkD7bz6t2GszxpK43A/pnl2vmYs+Lslty7hgTAk+ZdBs9+45Q7RwxVog5yoxWkC+MJPoYNPLYHIByiVD0doYvwmZuxBPsHgyOelHvFS4y+IlFiz/yrqnXT9v9M0J9cS0bb7DJFpgbM7x3z4RqIS6vFCT0iTsb4b3X21eJlCw4PsVTy1TH3FsMLUuxvX4wNLzTbsETF9PiwkjmnFAXKgzrrJkWhrp/knQtcFlaWhQ1EAZPDpvmdXKh3grcOKXdp9jzuPYv7UXqvWaTv4NNoeHXRBWfWsXcKSvzvfqrClAEJBF9ZAV+unBa44xMSlpes0Sr5DKBKR10HMriNvYlVrMpdriWH4uBIF7v1r4j73GSjkVoDqp0nQuFwEmuyuuRnhNdy3W42jKPlUabfbXmdf89Q6Lfk9dGSqKmBktkm28yNu0dBYabBqxaxL765eqejcobrcHBJXigNpD3fCi4Llk+TmkksGxUuBbNHAeE5NUTR4lQEjGjZdj5TbnIw6thcTcfcy69ZK60+0B4ZTrxJNl+LQ9IRUfltf4ZQ+zvteNn9ObKrFkhP7RF2Em8zjldOQ3QGYuEV3Z4BOcGDehyb3GP44Wr7jo45qFR6LVom9ZpwgkemwUzz4ttWAyE6kEDtMYsrtwGDwgW4GeuQLoooi1MjuCaPzd5U77TKWEHCn+zOMbx5tNC0KHhXt7Glb0YqxI3RWpB4kqu3+BJA1pWFL3C+F9I0hXpfyeZGBAO5zLGap1LLfT9c464+HT4f4pCKQJZDPMyf0PskvDDi17QCvy70UFLvezlt7hPyXcm7H6NDRQonkI75MN1UDFntVNysq5eSyxCXqP9jHesYN7R/A1DjNave1BPzWf6vmYw6ywcreEByEr+e66uHGOEivc9iQYvbw1aFcMz5hVCC8EQc2mIWGSMuWxLyQ3ItO3jsollU92bEXn5vwqRNIEDBJMIIDGgYJKoZIhvcNAQcBoIIDCwSCAwcwggMDMIIC/wYLKoZIhvcNAQwKAQKgggKmMIICojAcBgoqhkiG9w0BDAEDMA4ECML4YwV2JeiJAgIIAASCAoCq56QseqSNUcHXU3BXzRJWWdCe/HMkbgu7fJDbpZTqroSHLpVid9khmE0nojcvwkJm3isFarwGjMR2NoQHUAXkG6NayOHMOw//YgzqSygRWtCwQylNHv9DwQ3eOJKy1u23U2Z5Kwpe/oqTWve09BflmX0LZIfPNAE79YEgvq6pyzzkEFYw6cmvdobLZ5/pOLtHZpI5DgfDqYH/agmW2Zfylb/O1K4Wx2vRiXE+mkOiMzwZG0wddzO2AxAkdwjMzd2L+Ija1HnrPeCvy1CwST7q8kan1F+pfYoudTgYpMMJPFM2DQCWM22vYTEk2xwwBDc1NNk3X263ttV8dKtXGp3B984/ElDPav6HXETUk7FHnRHS+Qj9uMJDJk2TW2+EL296nvDg6JSHU9XF7CURJQZjQx6GQ/FJ20l/loxrD+peHsk64WmSnLrQr9WFreGUXrPhmQlt7lxX4Zudm8mxmvKwzm1zMUnHmPcYL9g7XbJO8oYqgqTofeTVJpG5bSQF3/TVFcldegEVQyk+qO/oAn6d7AMxQPKng0Dd8nFmbrxCxc49/V3NCXHaBPrb4Uupi4qYyYaYo8tTW52wfP4IKa+RVdb/PvsqIxbO9u/DtlroAat7vnPYhOPFEGV5f8I8LBSfkImOr0YqSS1pj7zRqIT0ShovUUkccd/Xjc6Fn8JknIhxQFl1NYmUxZ/omUKQkoIs3Dag3F94jqbRIhfrd74zUDzO3xJQ/KWZhTpTjIu3884vP3So9+rV4XZVqjiqaUp9AoKgNj4kQFTLo2vvXIs/XFmjFcDL7ERxLECCNlU1ezXmofcoD4+mg554vkUvKaTSYTG7qDJPx6XfVP77PWwxMUYwHwYJKoZIhvcNAQkUMRIeEAB0AGUAcwB0AHUAcwBlAHIwIwYJKoZIhvcNAQkVMRYEFJ89V9XN47rAEYowdptoEuIo871dMDEwITAJBgUrDgMCGgUABBRmXZXOFVPnLBxKEPz4Ec432TZtzwQIXjI2zO+u/h0CAggA");
		base64Encode.decodeStrNoToFile("MIIKlgYJKoZIhvcNAQcCoIIKhzCCCoMCAQExCzAJBgUrDgMCGgUAMIIB9AYJKoZIhvcNAQcBoIIB5QSCAeE8SFhFPjxIZWFkPjxJZGVudGlmaWNhdGlvbj4yMDE2MDExMzEzMzgwNTgxODQ0NDwvSWRlbnRpZmljYXRpb24+PFRybnhDb2RlPk1QMDE2PC9Ucm54Q29kZT48VHJueERhdGV0aW1lPjIwMTYwMTEzMTM1MDA5PC9Ucm54RGF0ZXRpbWU+PFJlc3BDb2RlPjAwMDAwMDwvUmVzcENvZGU+PFJlc3BEZXNjcD69u9LXtKbA7bPJuaajoTwvUmVzcERlc2NwPjwvSGVhZD48Qm9keT48VVJMPmh0dHBzOi8vMjIzLjcyLjE3NS4xNDE6NDQzL2VzYi9zbmV0R2F0ZT9vcmRlck5vPTU1QzgxQUM4QTg4OThCMEE3RjgxNDU3NzQwQTE3RDlBMkRDNzIxOERCMjQ3MUM4MSZtZXJjaGFudElkPUFGRDU4NTA4N0I0RjY5MzRGRjY3QTExM0VBRDI5RDEwJnJJbmZvcm1VUkw9PC9VUkw+PFJlbWFyazE+PC9SZW1hcmsxPjxSZW1hcmsyPjwvUmVtYXJrMj48UmVtYXJrMz48L1JlbWFyazM+PFJlbWFyazQ+PC9SZW1hcms0PjxSZW1hcms1PjwvUmVtYXJrNT48L0JvZHk+PC9IWEU+oIIHYDCCA6UwggKNoAMCAQICEBAAAAAAAAAAAAAAIACVWGcwDQYJKoZIhvcNAQEFBQAwWTELMAkGA1UEBhMCQ04xMDAuBgNVBAoTJ0NoaW5hIEZpbmFuY2lhbCBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTEYMBYGA1UEAxMPQ0ZDQSBURVNUIE9DQTExMB4XDTE0MDcyOTAzMjU1MVoXDTE3MDcyODAzMjU1MVowbzELMAkGA1UEBhMCQ04xFTATBgNVBAoTDENGQ0EgVEVTVCBDQTEMMAoGA1UECxMDSFhCMRQwEgYDVQQLEwtFbnRlcnByaXNlczElMCMGA1UEAxQcMDQxQDMxMjNAMDI1MDAwMDAwOUAwMDAwMDA0MjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA99YP8veGjXiA4QfrIyRdxcqlApqLbe/Rwbuy2wVmABpgYKy45KgFQVwhyXJVUfv5kWN+vJcT7oNPj5dSgscUIAHfgKAQlwDrCscauFmKhYcaynnl9j7NqSYCQVStQhgrGw2WMDan/nMYMygd6BPR2+kpD+mpXwurLX6KyNSFJ4ECAwEAAaOB1jCB0zAfBgNVHSMEGDAWgBT8C7xEmg4xoYOpgYcnHgVCxr9W+DAMBgNVHRMEBTADAQEAMDkGA1UdHwQyMDAwLqAsoCqGKGh0dHA6Ly8yMTAuNzQuNDEuODcvb2NhMTEvUlNBL2NybDg1OS5jcmwwCwYDVR0PBAQDAgTwMB0GA1UdDgQWBBSyqVpuuqLWLrf2obYAsBZNU1fkXzA7BgNVHSUENDAyBggrBgEFBQcDAgYIKwYBBQUHAwMGCCsGAQUFBwMEBggrBgEFBQcDAQYIKwYBBQUHAwgwDQYJKoZIhvcNAQEFBQADggEBAJ3QffQuowYoam3l3LbxmrHQCExbsVBqr26gapzjnyNZFyio03/xValc+p/MAsoUN5Wpcn1xACw+DkzTcQ+4OIDlcr+qzgzDXLXgFBfgpCLKKTxdLI9zZB3BXJHeHSZ9tIFqxAsGwFMRZbWt75mzCZcIRtb8lGGf1JvPUk35FslWEWTbfMQqMJ7o3uCO5jzXlejlhuTVexP4HKZLF6nID/wnOG5fEbVDhaLGEgLY/0D6kydKLD92JbYe+ZB4st8HpLbvW7yKKIPqH9VzP68irwUPe67z0BJR1DBefG0bjnlXPfEtwJV4//TJv/mC+9dTmIC0qNQZGe8Xr4cx0+jH+6kwggOzMIICm6ADAgECAgUgAAAABDANBgkqhkiG9w0BAQUFADBCMQswCQYDVQQGEwJDTjEZMBcGA1UEChMQQ0ZDQSBURVNUIFJTQSBDQTEYMBYGA1UEAxMPQ0ZDQSBDUyBURVNUIENBMB4XDTEyMDgyOTA1NTQzNloXDTMyMDgyNDA1NTQzNlowWTELMAkGA1UEBhMCQ04xMDAuBgNVBAoTJ0NoaW5hIEZpbmFuY2lhbCBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTEYMBYGA1UEAxMPQ0ZDQSBURVNUIE9DQTExMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvI59J/BaeoXEXQAnZ9fmOhgJAuihoDXSshz65rHZoQo53f4uKlBcJxvHKdw+JRcNRo5o0juclWG4pPzW0upWzaUYEyt3w3zBbyyaUSAviBYRvUnQAtocI7B/ak6S/XCtjG0nawuqQXQ4J282jPQBgReGOr14Y+Z2oeGTLAbw6dfS8145Rrkzy7gIL/WZVpJ7HUTe9DyAJr6JnddLql+BiCeCavxXSlzi2dgzSvzYUcjemKu9S94WpurbrV3maMObedNueAJ1JaNbuknh/RfIrREeud6P6DCxcKfut6lwHKqx1JTkD1/iSPH5LpkqmeBdhbfx31TJ6nbRTQp4efniAQIDAQABo4GYMIGVMB8GA1UdIwQYMBaAFHTexY0KfRAaqmmDW00hzoabzHE4MAwGA1UdEwQFMAMBAf8wOAYDVR0fBDEwLzAtoCugKYYnaHR0cDovLzIxMC43NC40MS44Ny9yY2Fyc2EvUlNBL2NybDEuY3JsMAsGA1UdDwQEAwIB/jAdBgNVHQ4EFgQU/Au8RJoOMaGDqYGHJx4FQsa/VvgwDQYJKoZIhvcNAQEFBQADggEBAAKHq4T5gQoD/I9AlvaN4DOBzjctCdDPo78QwkjMH2E9xKhKTZrhcV4eulS5se1h05mhR/inQeGjOgsN+HFZlXpY+OXptATvmMCtPodhrhW+qBqhUyM0+gUc/OLFugkzRv/UBXvL0934gS5jlW70/QI4j2IYJJxOtqxl5shI5xtu0q4N6m444aPOBVY4F+VMCC+C5rcDLyvJoigCVcOnE5261CIPkZhkkGGbKNjcS8l3cRG2S6Fw7tbch/AgyNbN8DNy70gDNMBfuKAqUvHtRCvHispiEIfOmKGN2ILj4uvrJtb3qcsoTSKvBqFBREQgsdeuPi7rsnzNRKYApFPMNgExggETMIIBDwIBATBtMFkxCzAJBgNVBAYTAkNOMTAwLgYDVQQKEydDaGluYSBGaW5hbmNpYWwgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkxGDAWBgNVBAMTD0NGQ0EgVEVTVCBPQ0ExMQIQEAAAAAAAAAAAAAAgAJVYZzAJBgUrDgMCGgUAMA0GCSqGSIb3DQEBAQUABIGA0QCORgSgbqhTaP8goezZ6/bxPMKnYAGx/7idyyCgDyB1JLD0W+aY9Dt1cDXjY5nLM3pyIP3hUoHwIB570wyyzjtbBndaC8FQDFL2fL6WR6fFX8YgavhhrguVfVyaNa21Rz9S35+GNuMJ7XajDA+bXzKLxUuolf3jYn8rV3EBkXA=");
		//base64Encode.decodeString("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");
	    
	}
	@Ignore
	@Test
	public void decode() throws IOException{
		String str ="/u3+7QAAAAIAAAADAAAAAgA8dmVyaXNpZ24gY2xhc3MgMyBwdWJsaWMgcHJpbWFyeSBjZXJ0aWZpY2F0aW9uIGF1dGhvcml0eSAtIGc1AAABTgsZ4NQABVguNTA5AAAE1zCCBNMwggO7oAMCAQICEBja0Z4mfei7SiFYzcxrO0owDQYJKoZIhvcNAQEFBQAwgcoxCzAJBgNVBAYTAlVTMRcwFQYDVQQKEw5WZXJpU2lnbiwgSW5jLjEfMB0GA1UECxMWVmVyaVNpZ24gVHJ1c3QgTmV0d29yazE6MDgGA1UECxMxKGMpIDIwMDYgVmVyaVNpZ24sIEluYy4gLSBGb3IgYXV0aG9yaXplZCB1c2Ugb25seTFFMEMGA1UEAxM8VmVyaVNpZ24gQ2xhc3MgMyBQdWJsaWMgUHJpbWFyeSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eSAtIEc1MB4XDTA2MTEwODAwMDAwMFoXDTM2MDcxNjIzNTk1OVowgcoxCzAJBgNVBAYTAlVTMRcwFQYDVQQKEw5WZXJpU2lnbiwgSW5jLjEfMB0GA1UECxMWVmVyaVNpZ24gVHJ1c3QgTmV0d29yazE6MDgGA1UECxMxKGMpIDIwMDYgVmVyaVNpZ24sIEluYy4gLSBGb3IgYXV0aG9yaXplZCB1c2Ugb25seTFFMEMGA1UEAxM8VmVyaVNpZ24gQ2xhc3MgMyBQdWJsaWMgUHJpbWFyeSBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eSAtIEc1MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAryQICCl6NZ5gDKrnSztO3Hy8PEUcuyvg/ikC+VcIo2SFFSf18a3IMYldIugqqqZCs4/4uVW3sbdLs/6PfgdX7O9D22ZiFWHPYA2k2N744MNiCD1UE+tJyllUhSblK48bn+v1oZHCM0nYQ2NqUkvSj+hwUU3RiWl7x3D2s9wSdNt7XUtW05a/FXehsPSiJfKvHJJnGOX0BgTvkLnkAOTdOrUZ/wK69Dzu4IvrN4vs9Nes8vbwPa/ddZEzGR0cQMt0JBkhk9kU/qwqUseP1QRJ5I1jR4g8aYPL/ke9K35PxZWuDp3U0UPAZ3PjFAh+5T+fc7gzCs9dPzSHloruU+glFQIDAQABo4GyMIGvMA8GA1UdEwEB/wQFMAMBAf8wDgYDVR0PAQH/BAQDAgEGMG0GCCsGAQUFBwEMBGEwX6FdoFswWTBXMFUWCWltYWdlL2dpZjAhMB8wBwYFKw4DAhoEFI/l0xqGrI2Oa8PPgGrUSBgsexkuMCUWI2h0dHA6Ly9sb2dvLnZlcmlzaWduLmNvbS92c2xvZ28uZ2lmMB0GA1UdDgQWBBR/02Wnwt3su/AwCfNDOfoCrzMxMzANBgkqhkiG9w0BAQUFAAOCAQEAkyRKMF9iz9gamC896tyZLb139qV5IjjsxKegeBKtYg5FcGTF55dmLZgJfl+v1swoZfIBqggaR975+XySWghpIA3ZPm1uPA1u2OYGkUAYufjB7d/bQargliDJzWQVOIHJlO6ihCkLE2+O2wzdJQLbpIsZRNJBegVpSlhPYMp+gmoLAqolFzm123/nhGUqlYq9ht5egRaDLRDM3v2ogiptKB8NC8Tl5xomGeH0EW8QtZX850IFMtvOnVFeKLaehdNb76V9RUByjrcOaw4G+zM1SHG4nSeLxGVfDYZ2nER69pVc9l0yCDOkVLYYP2hc8kJKhThUg1/R6CzyrBHWqO1jagAAAAIAbHZlcmlzaWduIGNsYXNzIDMgaW50ZXJuYXRpb25hbCBzZXJ2ZXIgY2EgLSBnMyAodmVyaXNpZ24gY2xhc3MgMyBwdWJsaWMgcHJpbWFyeSBjZXJ0aWZpY2F0aW9uIGF1dGhvcml0eSAtIGc1KQAAAU4LGa5aAAVYLjUwOQAABi0wggYpMIIFEaADAgECAhBkG+ggzgIIE/MtTS2V1n5nMA0GCSqGSIb3DQEBBQUAMIHKMQswCQYDVQQGEwJVUzEXMBUGA1UEChMOVmVyaVNpZ24sIEluYy4xHzAdBgNVBAsTFlZlcmlTaWduIFRydXN0IE5ldHdvcmsxOjA4BgNVBAsTMShjKSAyMDA2IFZlcmlTaWduLCBJbmMuIC0gRm9yIGF1dGhvcml6ZWQgdXNlIG9ubHkxRTBDBgNVBAMTPFZlcmlTaWduIENsYXNzIDMgUHVibGljIFByaW1hcnkgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkgLSBHNTAeFw0xMDAyMDgwMDAwMDBaFw0yMDAyMDcyMzU5NTlaMIG8MQswCQYDVQQGEwJVUzEXMBUGA1UEChMOVmVyaVNpZ24sIEluYy4xHzAdBgNVBAsTFlZlcmlTaWduIFRydXN0IE5ldHdvcmsxOzA5BgNVBAsTMlRlcm1zIG9mIHVzZSBhdCBodHRwczovL3d3dy52ZXJpc2lnbi5jb20vcnBhIChjKTEwMTYwNAYDVQQDEy1WZXJpU2lnbiBDbGFzcyAzIEludGVybmF0aW9uYWwgU2VydmVyIENBIC0gRzMwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCZ1pxi8BX0gZpBCFmPE50XyZ9R3NqxUu//40Hd4N/EKMbjrXkfJxCYuLsgl8EoREEP6qmoUs9NThuLu7XEdtnMVgbus1UgKt4VjXHLVMhvF82JAOTc/+HAH2hx6ccpLn68O/zlu6smVItmkM32krkxJIC8nmzV/H7S4UuM3EL6REtf+Bi1LjD0PRKY02IFc1SmnKIdvlKDOgdGxDsCViG/8lFP0KaZOemupT+Jm5x9/k1gByUg97vXaYMrgpNDN9mDQRtrC6tKZoRPSo7efjSZjmjWyjkGm0yzmkhNE0a0WCEExPugTawuS2IS4/tN9slRAAEf/B5qgSo44LlP1i1FAgMBAAGjggIVMIICETASBgNVHRMBAf8ECDAGAQH/AgEAMHAGA1UdIARpMGcwZQYLYIZIAYb4RQEHFwMwVjAoBggrBgEFBQcCARYcaHR0cHM6Ly93d3cudmVyaXNpZ24uY29tL2NwczAqBggrBgEFBQcCAjAeGhxodHRwczovL3d3dy52ZXJpc2lnbi5jb20vcnBhMA4GA1UdDwEB/wQEAwIBBjBtBggrBgEFBQcBDARhMF+hXaBbMFkwVzBVFglpbWFnZS9naWYwITAfMAcGBSsOAwIaBBSP5dMahqyNjmvDz4Bq1EgYLHsZLjAlFiNodHRwOi8vbG9nby52ZXJpc2lnbi5jb20vdnNsb2dvLmdpZjA0BgNVHSUELTArBggrBgEFBQcDAQYIKwYBBQUHAwIGCWCGSAGG+EIEAQYKYIZIAYb4RQEIATA0BggrBgEFBQcBAQQoMCYwJAYIKwYBBQUHMAGGGGh0dHA6Ly9vY3NwLnZlcmlzaWduLmNvbTA0BgNVHR8ELTArMCmgJ6AlhiNodHRwOi8vY3JsLnZlcmlzaWduLmNvbS9wY2EzLWc1LmNybDAoBgNVHREEITAfpB0wGzEZMBcGA1UEAxMQVmVyaVNpZ25NUEtJLTItNzAdBgNVHQ4EFgQU15t82CKgFffdrV/OKZtYw7xGALUwHwYDVR0jBBgwFoAUf9Nlp8Ld7LvwMAnzQzn6Aq8zMTMwDQYJKoZIhvcNAQEFBQADggEBAHG1fXNSSt3XTTQrLq+URqVJUAJP+C8XcPIT3B8hhqrCT3w3PNRGeK5deG/Rulq8EKtYNsWMYhVFYBch4tVCqHehVdhDBFH2brpI5l1Mt0TTPqTV1jOanw3m106WRJVabNajFlMOmEPOpLjDZnoFXGIQ6BsS230udlD/39drG8yKzHH6s0BWfDN6d5Rb9QtT+w5fvGj7ryruMDd5FpMlf00Q/1f7v247MyHeedyGF1ktQ2S3pmaH6ryWRhkahotv17dJAFvbo78pmu730zOuo/SeTMpeadQbrbeQd2rYWW95qwH6VfCKIWblZW79fNPfHut+PwaQ+xkL0wYCG3hDmagAAAABADcwNDFANzY5NzI5Nzg4NkBlc2IwNDUwMDAwMDk5QDAwMDAwMDAyIChjZmNhIHRlc3Qgb2NhMTEpAAABTgsZBc4AAAK6MIICtjAOBgorBgEEASoCEQEBBQAEggKidyuTniG81tb1Urw8ZQ2Np81cDzukpUOcngJTHsB6spWna804dfNb+473O2I8QEauj4DZNifPvm7QPbvFsXW4241QDj7xuuQX7nirvYcJtje1u8KW4cA4pbws6gwiemyjZdbBPu47mwsUVQXc4RZ4bGOMwf3Rziqh/U/2B931x9GOp3Jo0+e3eQdaTqZBNCBKkavgp4lQrIX+lkj3Me5pYv58R1qSJRkdTJi91Fu+G8w6meG2tfl8M7j8SRcwvRyAhSAz+gpJpTb4rTtWC1BC7EY1ggjEZUD/HoTcFNXBPY5tDUAX/T1bN40y96HxeRqxKEMXkVO7q+YZnoaEhIOw0c+YC1gYpfz073IwmitEgUhj5+Q0CVMlwfAqS0ohcZ2vohPpIwjGJZLa3QeHnBPZw2WfeCY12HkHcwf4yMs50p19Q/KePV4ZhP0yHhUIQIGKSyUrqq13Hy9oZxGEnaBUVI4wXOUv5CbRtaLCS4b4tJM1br6YcOCWnCKz800Q2InRjm/ShyjYocVsDXvQPfuK7KmHUkO3UpufLQISqIUgGiwt7+BI5XgtxJuFkksyy6JljVZ9pjWHlrCGoY6o4kmNFsWtqcGTvBpttyIR9QovUlnmBJ/kyDGctg9kXMnKzxR+a8LCf+LBmqJcY7H3r1H7+hTwrQMZlPX+kdCxqNNuMrdnktXa1OD3KnDB5+9gOVCp4+fF6PNLPma1hRZsy5ZPTUdRhxPe1M+98FDles2G/PyoPsvGXEuW2ci+Meb300zDdt3bpDV2ett3QnIygJCozktICcyH0aVL0QuZacgbqfAARheggNKJAJuNYwj9cBV3wSM58TBz7IoF4aXKiYS4kRGdLo/NM8mhNk1hpEVXl+EC3AjIsR71iuNZy//ybdjXkHAAAAABAAVYLjUwOQAAA68wggOrMIICk6ADAgECAhAQAAAAAAAAAAAAACADV0kyMA0GCSqGSIb3DQEBBQUAMFkxCzAJBgNVBAYTAkNOMTAwLgYDVQQKEydDaGluYSBGaW5hbmNpYWwgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkxGDAWBgNVBAMTD0NGQ0EgVEVTVCBPQ0ExMTAeFw0xNTA2MTcwMzE0MjhaFw0xNjA2MTcwMzE0MjhaMHgxCzAJBgNVBAYTAkNOMRUwEwYDVQQKEwxDRkNBIFRFU1QgQ0ExDDAKBgNVBAsTA0hYQjEUMBIGA1UECxMLRW50ZXJwcmlzZXMxLjAsBgNVBAMUJTA0MUA3Njk3Mjk3ODg2QGVzYjA0NTAwMDAwOTlAMDAwMDAwMDIwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAKEwBssFhCOU8FGv9c37rkVxe3eAi+OzmLjaZ/NFPmPWT8rHlx8Wb6e8UnI6B7xzQIyNhSQkCS9vaL6j9pMPJILjDIokUIAOZmi6Z/Ei/rGG+gdn1860Ovgfk3agqFYOwL6XplEhoLP4zAHMntpvQm37QPCewTHVFuTczSaMa8exAgMBAAGjgdMwgdAwHwYDVR0jBBgwFoAU/Au8RJoOMaGDqYGHJx4FQsa/VvgwCQYDVR0TBAIwADA5BgNVHR8EMjAwMC6gLKAqhihodHRwOi8vMjEwLjc0LjQyLjMvT0NBMTEvUlNBL2NybDIyMzcuY3JsMAsGA1UdDwQEAwIE8DAdBgNVHQ4EFgQUj+0x+Ko8CEOyIX/ibBsc3rUs1H0wOwYDVR0lBDQwMgYIKwYBBQUHAwIGCCsGAQUFBwMDBggrBgEFBQcDBAYIKwYBBQUHAwEGCCsGAQUFBwMIMA0GCSqGSIb3DQEBBQUAA4IBAQCGfSpJTR6rnJOZlaUhb6QRuZwIFnIgC6bTlbxPZpYxvbB+r7JAV2gjaYQFUVmauSHyOr6tKNYZJDwGAaNxAlPqP4xv78kp5iQ+LhWbyGXoFQ2S7Bf4OGUSpU1CMtgK/Rh9TqFjNLOG9X2BfwW5Y8MaclMxlS3iSFgFcGU3qq6NfiJftZF3t3gT4itfzPmFH/Yw2eEty7JiB6WEMMB1+XrgJwVkJEIZoc0zcVROvzAkXIlJbnAMmugn+K6ioF13o4yO8YIqdJtlq/6YoP5nFZ8Wqmhc/U3JoP4tLojVlvMFeA5453ptN2aZDWuNMVwdQtyEBOVNaLRMXIRKSwnHqYpu2ZCFYzhWI5u8kGsszZscMoXyZuw=";//azFpMmQzZDQ=
		//str ="Nzc0NDgyNDU3NjIwNDgyOTQ4Mjg3NTY0";
		String strtmp = base64Encode.decodeStrNoToFile(str);
		System.out.println(strtmp);//Base64.decodeBase64(
		/*byte[] rt2 = str.getBytes();*/
		
		//InputStream input = this.getClass().getResourceAsStream("/jks/https.jks");
		//byte[] rt = IOUtils.toByteArray(input);
		/*System.out.println(rt2.length+"====="+rt.length);
		byte[] lenght = new byte[10];
		System.arraycopy(rt,0,lenght,0,10);
		System.out.println(new String(lenght));*/
		//base64Encode.encode(rt);
		//System.out.println(new String(strtmp.getBytes(),"UTF-8"));
	}
	@Ignore
	@Test
	public void fileToString() throws Exception{
		InputStream input = this.getClass().getResourceAsStream("/jks/wxl.zip");
		byte[] inputbyte = IOUtils.toByteArray(input);
		File file = new File("D:/wxl.zip");
		file.createNewFile();
		OutputStream output = new FileOutputStream(file);
		//IOUtils.copy(input, output);
		IOUtils.write(inputbyte, output);
		output.close();
		input.close();
	}

}
