package homeWork_Modul11_5;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


public class Smeshat {
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Builder<T> builder = Stream.builder();

        TestStream<T> testFirst = new TestStream<>(first);
        TestStream<T> testSecond = new TestStream<>(second);

        while (testFirst.hasValue() && testSecond.hasValue()) {
            builder.accept(testFirst.next());
            builder.accept(testSecond.next());
        }

        return builder.build();
    }
    private static class TestStream<T> {
        private final Iterator<T> iterator;

        public TestStream(Stream<T> stream) {
            this.iterator = stream.iterator();
        }

        public boolean hasValue() {
            return iterator.hasNext();
        }

        public T next() {
            if (!hasValue()) {
                throw new IllegalStateException("нет элементов");
            }
            return iterator.next();
        }
    }

    public static void main(String[] args) {
        Stream<Integer> first = Stream.of(1, 3, 5);
        Stream<Integer> second = Stream.of(2, 4, 6, 7);

        Stream<Integer> zipStream = zip(first, second);
        zipStream.forEach(obj -> System.out.print(obj + " "));
    }
}


