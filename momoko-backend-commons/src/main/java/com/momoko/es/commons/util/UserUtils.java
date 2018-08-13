package com.momoko.es.commons.util;

import com.momoko.es.commons.security.UserDto;
import com.momoko.es.commons.security.UsuarioDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserUtils {

	private static final Log log = LogFactory.getLog(UserUtils.class);

	public static final int EMAIL_MIN = 4;
	public static final int EMAIL_MAX = 250;
	public static final int UUID_LENGTH = 36;
	public static final int PASSWORD_MAX = 50;
	public static final int PASSWORD_MIN = 6;
	public static final int URL_MIN = 3;
	public static final int URL_MAX = 100;

	/**
	 * Role constants. To allow extensibility, this couldn't be made an enum
	 */
	public interface Role {

		static final String UNVERIFIED = "UNVERIFIED";
		static final String BLOCKED = "BLOCKED";
		static final String ADMIN = "ADMIN";
	}

	public interface Permission {

		static final String EDIT = "edit";
		static final String ADMIN = "admin";
	}

	// validation groups
	public interface SignUpValidation {
	}

	public interface UpdateValidation {
	}

	public interface ChangeEmailValidation {
	}

	// JsonView for Sign up
	public interface SignupInput {
	}

	public static <ID> boolean hasPermission(Integer id, UsuarioDTO<Integer> currentUser, String permission) {

		log.debug("Computing " + permission + " permission for User " + id + "\n  Logged in user: " + currentUser);

		if (permission.equals("admin")) {

			if (currentUser == null)
				return false;

			return currentUser.isGoodAdmin();
		}

		return false;
	}


}
