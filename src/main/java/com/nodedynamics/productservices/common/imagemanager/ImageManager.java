package com.nodedynamics.productservices.common.imagemanager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.nodedynamics.productservices.common.Util;
import com.nodedynamics.productservices.model.common.ImageModel;

public class ImageManager {
	
	Logger log = LoggerFactory.getLogger(ImageManager.class);
	
	private Storage storage;
	
	
	public void Connect() {
		
		try {
			StorageOptions storageOptions = StorageOptions.newBuilder()
				       .setProjectId("YOUR_PROJECT_ID")
				        .setCredentials(GoogleCredentials.fromStream(new 
				         FileInputStream("D:\\NodeDynamics\\project\\3.internal\\EvntxzV2\\001.Code\\evntxz001-fb83bb308486.json"))).build();
			
			this.storage = storageOptions.getService();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		
	}
	
	public ImageModel Commit(ImageModel imageModel) {
		ImageModel IM=null;
		
		//CHECK IF IMAGE EXIST
		if(!(imageModel.getName().isEmpty())) {
			
			try {
				//byte[] imageByteArray=new byte[imageModel.getBase64().length()];
				//log.info(imageByteArray.toString());
				String imageDataBytes = imageModel.getBase64().substring(imageModel.getBase64().indexOf(",")+1);
				log.info(imageDataBytes);
				byte[]imageByteArray=Base64.getDecoder().decode(imageDataBytes);
				
				
				InputStream in = new ByteArrayInputStream(imageByteArray);
				BufferedImage image = ImageIO.read(in);
	
				
				//GET IMAGE TYPE
				int type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
				
				//RESIZE IMAGE
				BufferedImage resizedImage = resize(image, 500, 500, type);
				
				
				
				//TEMP WRITE IMAGE
				File output =  new File("d:/imageTemp/"+imageModel.getName());
				
				String FileExt=Util.getFileExtension(imageModel.getName());
				ImageIO.write(resizedImage, FileExt, output);

				
				//COMMIT OBJECT
				//this.conn.putObject(new PutObjectRequest(Global.STORAGE.FOLDER.key, imageModel.getImageName(), output).withCannedAcl(CannedAccessControlList.PublicRead));
				BlobId blobId = BlobId.of("evntxz-product", imageModel.getName());
				BlobInfo blobInfo;
				
				if(FileExt.contains("png")) {
					 blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/png").build();
					 byte[] bytesArray = null;
						FileInputStream f = new FileInputStream(output);
						
						
						Blob blob = storage.create(blobInfo,f);
				}else {
					 log.info("image format not correct");
				}
				
			
				
				//BUILD MODEL
				IM=ImageModel.builder().imageLoc("https://storage.googleapis.com/evntxz-product/"+imageModel.getName()).imageName(imageModel.getImageName())
						.build();
				return IM;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		return IM;
		
		
	}
	
	private static BufferedImage resize(BufferedImage img, int IMG_HEIGHT, int IMG_WIDTH, int type) {
		
		int orgHeightImageSize=img.getHeight();
		int orgWidthImageSize=img.getWidth();
		
		int newHeight;
		int newWidth;
		  
		if(orgHeightImageSize > orgWidthImageSize){
			newHeight=IMG_HEIGHT;
			newWidth=Math.round((orgWidthImageSize*(IMG_HEIGHT/orgHeightImageSize)));			
		 }else{
			newWidth=IMG_WIDTH;
			
			float widthPercentage=(float)((float)IMG_WIDTH/(float)orgWidthImageSize);
			newHeight=(int)Math.round(((float)orgHeightImageSize*((float)widthPercentage))); 
		 }		 
		 BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, type);
		 Graphics2D g = resizedImage.createGraphics();
		 g.drawImage(img, 0, 0, newWidth, newHeight, null);
		 g.dispose();
				
		 return resizedImage;	    
	}

}

