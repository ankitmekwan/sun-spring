package com.computers.sun.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.security.Principal;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.computers.sun.command.RequestUrlInfo;
import com.computers.sun.exception.SpringException;
import com.computers.sun.service.IpRequestInfoService;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;

@Controller
public class MainController {
    private static final Logger LOGGER = LogManager
            .getLogger(MainController.class);

    @Autowired
    ServletContext context;

    @Autowired
    private IpRequestInfoService ipRequestInfoService;

    public IpRequestInfoService getIpRequestInfoService() {
        return ipRequestInfoService;
    }

    public void setIpRequestInfoService(
            final IpRequestInfoService ipRequestInfoService) {
        this.ipRequestInfoService = ipRequestInfoService;
    }

    @RequestMapping(value = { "/", "/login.htm" }, method = RequestMethod.GET)
    public ModelAndView loginPage(final HttpServletRequest request,
            final HttpServletResponse response, final Principal principal)
                    throws IOException, SpringException {

        LOGGER.info("MainController : loginPage");

        final ModelAndView mv = new ModelAndView("login");

        String ipAddress = request.getHeader("proxy-ip");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress == null) {
                response.sendRedirect(request.getRequestURL().substring(0,
                        request.getRequestURL().lastIndexOf("/") + 1)
                        + "login");
            }
        }

        final InetAddress addr = InetAddress.getByName(ipAddress);
        final String strloginHostName = addr.getHostName();
        final String strlocalloginIPAddress = addr.getHostAddress();

        final String webInfPath = context
                .getRealPath("/location/GeoLiteCity.dat");

        String country = "";
        String region = "";
        String city = "";
        if (webInfPath != null) {
            try {
                final File file = new File(webInfPath);
                final LookupService lookup = new LookupService(file,
                        LookupService.GEOIP_MEMORY_CACHE);
                final Location locationServices = lookup
                        .getLocation(strlocalloginIPAddress);
                if (locationServices != null) {
                    country = locationServices.countryName != null
                            ? locationServices.countryName : "";
                    region = locationServices.region != null ? regionName
                            .regionNameByCode(locationServices.countryCode,
                                    locationServices.region)
                            : "";
                    city = locationServices.city != null ? locationServices.city
                            : "";
                }
            } catch (final Exception e) {
                LOGGER.error("Error: ", e);
            }
        }

        String browserName = null;
        String clientOS = null;
        String userAgent = request.getHeader("User-Agent");
        userAgent = userAgent != null ? userAgent.toLowerCase() : "";

        if (userAgent.toUpperCase().contains("WIN")) {
            if (userAgent.toUpperCase().contains("WINDOWS NT 10")
                    || userAgent.toUpperCase().contains("WINDOWS 10")) {
                clientOS = "Windows 10";
            } else if (userAgent.toUpperCase().contains("WINDOWS 8.1")
                    || userAgent.toUpperCase().contains("WINDOWS NT 6.3")) {
                clientOS = "Windows 8.1";
            } else if (userAgent.toUpperCase().contains("WINDOWS 8")
                    || userAgent.toUpperCase().contains("WINDOWS NT 6.2")) {
                clientOS = "Windows 8.1";
            } else if (userAgent.toUpperCase().contains("WINDOWS 7")
                    || userAgent.toUpperCase().contains("WINDOWS NT 6.1")) {
                clientOS = "Win7";
            } else if (userAgent.toUpperCase().contains("WINDOWS NT 5.1")
                    || userAgent.toUpperCase().contains("WINDOWS XP")) {
                clientOS = "WinXP";
            } else if (userAgent.toUpperCase().contains("WINDOWS NT 6.0")) {
                clientOS = "WinVista/Server 08";
            } else if (userAgent.toUpperCase().contains("WINDOWS ME")) {
                clientOS = "WinME";
            } else if (userAgent.toUpperCase().contains("WINDOWS NT 4.0")
                    || userAgent.toUpperCase().contains("WINNT4.0")
                    || userAgent.toUpperCase().contains("WINNT")) {
                clientOS = "WinNT";
            } else if (userAgent.toUpperCase().contains("WINDOWS NT 5.2")) {
                clientOS = "WinServer 03";
            } else if (userAgent.toUpperCase().contains("WINDOWS NT 5.0")
                    || userAgent.toUpperCase().contains("WINDOWS 2000")) {
                clientOS = "Win2000";
            } else if (userAgent.toUpperCase().contains("WINDOWS 98")
                    || userAgent.toUpperCase().contains("WIN98")) {
                clientOS = "Win98";
            } else if (userAgent.toUpperCase().contains("WINDOWS 95")
                    || userAgent.toUpperCase().contains("WIN95")
                    || userAgent.toUpperCase().contains("WINDOWS_95")) {
                clientOS = "Win95";
            } else if (userAgent.contains("WIN16")) {
                clientOS = "Win3.1";
            } else {
                clientOS = "WinVer.Unknown";
            }
            if ((userAgent.toUpperCase().contains("WOW64"))
                    || (userAgent.toUpperCase().contains("X64"))
                    || (userAgent.toUpperCase().contains("WIN64"))
                    || (userAgent.toUpperCase().contains("IA64"))) {
                clientOS = clientOS + "(x64)";
            } else {
                clientOS = clientOS + "(x32)";
            }
        } else if (userAgent.toUpperCase().contains("IPHONE")
                || userAgent.toUpperCase().contains("IPAD")
                || userAgent.toUpperCase().contains("IPOD")) {
            clientOS = "iOS";
        } else if (userAgent.contains("Mac OS X")) {
            clientOS = "Mac OS X";
        } else if (userAgent.toUpperCase().contains("UNIX")) {
            clientOS = "Unix";
        } else if (userAgent.toUpperCase().contains("LINUX")
                || userAgent.toUpperCase().contains("X11")) {
            clientOS = "Linux";
        }

        if (userAgent.contains("msie"))
            browserName = "IE";
        else if (userAgent.contains("opera"))
            browserName = "Opera";
        else if (userAgent.contains("chrome"))
            browserName = "Chrome";
        else if (userAgent.contains("firefox"))
            browserName = "Firefox";
        else if (userAgent.contains("safari") && userAgent.contains("version"))
            browserName = "Safari";

        final RequestUrlInfo requestUrlInfo = new RequestUrlInfo();
        requestUrlInfo.setIpAddress(strlocalloginIPAddress);
        requestUrlInfo.setOperatingSystem(clientOS);
        requestUrlInfo.setBrowser(browserName);
        requestUrlInfo.setCountry(country);
        requestUrlInfo.setRegion(region);
        requestUrlInfo.setCity(city);
        requestUrlInfo.setLanguage("");
        requestUrlInfo.setOrganization("");
        requestUrlInfo.setHostName(strloginHostName);
        requestUrlInfo.setCreatedDate(new Date());
        getIpRequestInfoService().saveIpInfo(requestUrlInfo);

        if (principal != null) {
            response.sendRedirect(request.getRequestURL().substring(0,
                    request.getRequestURL().lastIndexOf("/") + 1)
                    + "admin.htm");
        }
        return mv;

    }

    @RequestMapping(value = "/admin.htm*", method = RequestMethod.GET)
    public String adminPage(final Model model) {
        LOGGER.info("MainController : admin");
        model.addAttribute("title", "admin.page.title");
        model.addAttribute("message", "admin.page.message");
        return "admin/home";
    }

    @RequestMapping(value = "/accessDenied.htm", method = RequestMethod.GET)
    public String accessDenied(final Model model, final Principal principal) {
        LOGGER.info("MainController : accessDenied");
        return "accessDenied";
    }
}