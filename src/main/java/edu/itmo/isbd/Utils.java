package edu.itmo.isbd;

import edu.itmo.isbd.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {
//	public static boolean checkAuthorities(Authentication auth, String username, List<UserService.Role> roles) {
//		for (String r : roles.stream().map(role -> role.name()).collect(Collectors.toList())) {
//			boolean result = !(auth.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals(r))
//					|| (auth.getName().equals(username)
//					&& auth.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("FARMER"))));
//		}
//		return false;
//	}

    public static List<String> getRoles(Authentication auth){
        return auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
