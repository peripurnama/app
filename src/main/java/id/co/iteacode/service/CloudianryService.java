package id.co.iteacode.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudianryService {

	// Cloudinary cloud_name, API_Key and API_Secret
	private static final String CLOUDINARY_CLOUD_NAME = "iteacode";
	private static final String CLOUDINARY_API_KEY = "464946816123139";
	private static final String CLOUDINARY_API_SECRET = "Y_MeQb1Tx2N1L5BeltWhpCEsWXs";
	private static final String CLOUDINARY_URL = "cloudinary://464946816123139:Y_MeQb1Tx2N1L5BeltWhpCEsWXs@iteacode";

	private Cloudinary cloudinary;

	public CloudianryService() {
		cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", CLOUDINARY_CLOUD_NAME, "api_key",
				CLOUDINARY_API_KEY, "api_secret", CLOUDINARY_API_SECRET, "secure", true));
	}

	public Map<String, Object> uploadToCloudinary(Cloudinary cloudinary, MultipartFile sourceFile) throws IOException {

		Transformation incoming = new Transformation().crop("limit").width(1000).height(1000);
		List<Transformation> eager = Arrays.asList(new Transformation().crop("fill").width(150).height(150));

		Map<String, Object> cloudinaryUrl = null;
		Map params = ObjectUtils.asMap("public_id", "djepati/" + formatImageName(sourceFile.getOriginalFilename()),
				incoming, eager);

		// Convert multipart file type image to File type because Cloudinary doesn't
		// accept multipart file type.
		File convFile = multipartToFile(sourceFile);

		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> result = (Map<String, Object>) cloudinary.uploader().upload(convFile, params);
			cloudinaryUrl = result;
		} catch (IOException e) {
			System.out.println("Could not upload file to Cloundinary from MultipartFile "
					+ sourceFile.getOriginalFilename() + e.toString());
			throw e;
		}

		return cloudinaryUrl;
	}

	public File multipartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	public Cloudinary getCloudinary() {
		return cloudinary;
	}

	public String formatImageName(String filename) {
		String str = filename.split("\\.", 3)[0];
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss-");
		String format = dateFormat.format(new Date());
		return format + str;
	}

}
