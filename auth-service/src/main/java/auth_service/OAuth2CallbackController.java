//package auth_service;
//
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/oauth2/callback")
//public class OAuth2CallbackController {
//
//    @GetMapping
//    public String callback(OAuth2AuthenticationToken authentication) {
//        // Handle the callback logic here
//        // Extract the access token from the authentication object
//        // Access protected resources using the access token
//        return "Callback Successful";
//    }
//
//}