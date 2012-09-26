package com.shizhong.web.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.shizhong.web.meta.Command;
import com.shizhong.web.bizmodel.EntityType;

public class CommandTest {

	@Test
	public void testTypeString() {
		EntityType liganType = Command.entityType("ligan");
		assertEquals(EntityType.LiGan, liganType);
		
		EntityType liganPreviewType = Command.entityType("liganPreview");
		assertEquals(EntityType.LiGanPreview, liganPreviewType);
		
		liganPreviewType = Command.entityType("LIGANPREVIEW");
		assertEquals(EntityType.LiGanPreview, liganPreviewType);
		
		liganPreviewType = Command.entityType("TYpe");
		assertNull(liganPreviewType);
	}

	@Test
	public void testTypeServletPath() {
		Command cmd = new Command("liganpreview-show");
		EntityType liganType = cmd.entityType();
		assertEquals(EntityType.LiGanPreview, liganType);
		
		cmd = new Command("ligan-show");
		liganType = cmd.entityType();
		assertEquals(EntityType.LiGan, liganType);
		
		cmd = new Command("/test5");
		liganType = cmd.entityType();
		assertNull(liganType);
		
		cmd = new Command("/te");
		liganType = cmd.entityType();
		assertNull(liganType);
	}

}
