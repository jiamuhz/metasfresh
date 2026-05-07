package de.metas.impexp;

import java.util.ArrayList;
import java.util.function.Supplier;

import lombok.NonNull;
import lombok.ToString;

@ToString
public class RecordingSupplier<T> implements Supplier<T>
{
	public static <T> RecordingSupplier<T> wrap(@NonNull final Supplier<T> delegate)
	{
		if (delegate instanceof RecordingSupplier)
		{
			return (RecordingSupplier<T>)delegate;
		}
		else
		{
			return new RecordingSupplier<>(delegate);
		}
	}

	private Supplier<T> delegate;

	private final ArrayList<T> previousValues = new ArrayList<>();

	private RecordingSupplier(@NonNull final Supplier<T> delegate)
	{
		this.delegate = delegate;
	}

	@Override
	public T get()
	{
		T value = delegate.get();
		previousValues.add(value);
		return value;
	}

	public ArrayList<T> getPreviousValues()
	{
		return new ArrayList<>(previousValues);
	}
}
