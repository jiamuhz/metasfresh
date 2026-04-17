package de.metas.ui.web.handlingunits;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import de.metas.handlingunits.HuId;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowDocumentTypeId;

 

public class HUEditorRowTest
{
	@Test
	public void testCreateHUEditorRow()
	{
		final HuId huId = HuId.ofRepoId(10);
		final HuId topLevelHUId = HuId.ofRepoId(20);
		final int windowId = 123;

		final HUEditorRow huEditorRow = HUEditorRow.builder(WindowDocumentTypeId.of(windowId))
				.setRowId(HUEditorRowId.ofHU(huId, topLevelHUId))
				.setType(HUEditorRowType.TU)
				.setTopLevel(false)
				.build();
		assertThat(huEditorRow.getHuId()).isEqualTo(huId);

		final DocumentId documentId = huEditorRow.getHURowId().toDocumentId();
		assertThat(documentId.isInt()).isFalse();
		assertThat(documentId.toString()).isEqualTo(huId.getRepoId() + "_T" + topLevelHUId.getRepoId()); // expecting 10_T20

		final DocumentPath documentPath = huEditorRow.getDocumentPath();
		assertThat(documentPath.getWindowId().toInt()).isEqualTo(windowId);
		assertThat(documentPath.getDocumentId().toInt()).isEqualTo(huId.getRepoId());
	}

}
