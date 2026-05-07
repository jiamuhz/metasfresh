package de.metas.inoutcandidate.spi.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import de.metas.handlingunits.expectations.AbstractHUExpectation;
import de.metas.handlingunits.expectations.IExpectationProducer;
import de.metas.handlingunits.model.I_M_ReceiptSchedule;
import de.metas.product.ProductId;
import de.metas.uom.UomId;
import de.metas.util.Check;

public class QualityExpectations<ParentExpectationType> extends AbstractHUExpectation<ParentExpectationType>
{
	public static final QualityExpectations<Object> newInstance()
	{
		return new QualityExpectations<>(null);
	}

	/** Creates a new {@link QualityExpectation} */
	private final IExpectationProducer<QualityExpectation<QualityExpectations<ParentExpectationType>>> newQualityExpectationProducer = new IExpectationProducer<QualityExpectation<QualityExpectations<ParentExpectationType>>>()
	{
		@Override
		public QualityExpectation<QualityExpectations<ParentExpectationType>> newExpectation()
		{
			final QualityExpectation<QualityExpectations<ParentExpectationType>> e = new QualityExpectation<>(QualityExpectations.this);
			if (receiptSchedule != null)
			{
				e.receiptSchedule(receiptSchedule);
			}
			return e;
		}

		@Override
		public void addToParent(final QualityExpectation<QualityExpectations<ParentExpectationType>> expectation)
		{
			Check.assumeNotNull(expectation, "expectation not null");
			expectations.add(expectation);
		}
	};

	// Expectations
	private I_M_ReceiptSchedule receiptSchedule = null;
	private QualityExpectation<QualityExpectations<ParentExpectationType>> aggregatedExpectation;
	private final List<QualityExpectation<QualityExpectations<ParentExpectationType>>> expectations = new ArrayList<>();

	public QualityExpectations(final ParentExpectationType parentExpectation)
	{
		super(parentExpectation);
	}

	public QualityExpectations<ParentExpectationType> assertExpected(final HUReceiptLineCandidatesBuilder actual)
	{
		final String prefix = "";

		Assert.assertNotNull(prefix + "HUReceiptLineCandidatesBuilder not null", actual);

		if (aggregatedExpectation != null)
		{
			aggregatedExpectation.assertExpected(prefix + "\nInvalid Aggregated expectation", actual);
		}

		final List<HUReceiptLineCandidate> receiptLineCandidates = actual.getHUReceiptLineCandidates();
		Assert.assertEquals(prefix + "receiptLineCandidates count not match", expectations.size(), receiptLineCandidates.size());

		for (int i = 0; i < expectations.size(); i++)
		{
			expectations.get(i).assertExpected(prefix + "\nIndex " + i, receiptLineCandidates.get(i));
		}

		return this;
	}

	public HUReceiptLineCandidatesBuilder createHUReceiptLineCandidatesBuilder()
	{
		Assert.assertNotNull("receipt schedule shall be set to " + this, receiptSchedule);

		final HUReceiptLineCandidatesBuilder huReceiptLineCandidatesBuilder = new HUReceiptLineCandidatesBuilder(receiptSchedule);

		final ProductId productId = ProductId.ofRepoId(receiptSchedule.getM_Product_ID());
		final UomId uomId = UomId.ofRepoId(receiptSchedule.getC_UOM_ID());

		for (final QualityExpectation<QualityExpectations<ParentExpectationType>> expectation : expectations)
		{
			final HUReceiptLinePartCandidate part = expectation.createPart(productId, uomId);
			huReceiptLineCandidatesBuilder.add(part);
		}

		return huReceiptLineCandidatesBuilder;
	}

	//
	//
	// Expectations
	//
	//

	public QualityExpectation<QualityExpectations<ParentExpectationType>> newQualityExpectation()
	{
		final QualityExpectation<QualityExpectations<ParentExpectationType>> e = newQualityExpectationProducer.newExpectation();
		newQualityExpectationProducer.addToParent(e);
		return e;
	}

	public QualityExpectation<QualityExpectations<ParentExpectationType>> qualityExpectation(final int index)
	{
		return expectations.get(index);
	}

	public QualityExpectations<ParentExpectationType> receiptSchedule(final I_M_ReceiptSchedule receiptSchedule)
	{
		this.receiptSchedule = receiptSchedule;
		return this;
	}

	public QualityExpectation<QualityExpectations<ParentExpectationType>> aggregatedExpectation()
	{
		if (aggregatedExpectation == null)
		{
			aggregatedExpectation = newQualityExpectationProducer.newExpectation();
		}
		return aggregatedExpectation;
	}
}
