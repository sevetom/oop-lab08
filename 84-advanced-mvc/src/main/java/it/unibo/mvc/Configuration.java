package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Encapsulates the concept of configuration.
 */
public final class Configuration {

    private final int max; 
    private final int min;
    private final int attempts;

    private Configuration(final int max, final int min, final int attempts) {
        this.max = max;
        this.min = min;
        this.attempts = attempts;
    }

    /**
     * @return the maximum value
     */
    public int getMax() {
        return max;
    }

    /**
     * @return the minimum value
     */
    public int getMin() {
        return min;
    }

    /**
     * @return the number of attempts
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * @return true if the configuration is consistent
     */
    public boolean isConsistent() {
        return attempts > 0 && min < max;
    }

    /**
     * Pattern builder: used here because:
     * 
     * - all the parameters of the Configuration class have a default value, which
     * means that we would like to have all the possible combinations of
     * constructors (one with three parameters, three with two parameters, three
     * with a single parameter), which are way too many and confusing to use
     * 
     * - moreover, it would be impossible to provide all of them, because they are
     * all of the same type, and only a single constructor can exist with a given
     * list of parameter types.
     * 
     * - the Configuration class has three parameters of the same type, and it is
     * unclear to understand, in a call to its contructor, which is which. By using
     * the builder, we emulate the so-called "named arguments".
     * 
     */
    public static class Builder {

        private static final int MIN = 0;
        private static final int MAX = 100;
        private static final int ATTEMPTS = 10;

        private int min;
        private int max;
        private int attempts;
        private boolean consumed = false;

        /**
         * Loads settings from file.
         * 
         * @param congfigFile
         */
        private void loadSettings(final String congfigFile) {
            final InputStream in = ClassLoader.getSystemResourceAsStream(congfigFile);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            try {
                final String minLine = br.readLine();
                final String maxLine = br.readLine();
                final String attLine = br.readLine();
                String[] mins = minLine.split("\\s");
                String[] maxs = maxLine.split("\\s");
                String[] atts = attLine.split("\\s");
                this.setMin(Integer.parseInt(mins[1]));
                this.setMax(Integer.parseInt(maxs[1]));
                this.setAttempts(Integer.parseInt(atts[1]));
            } catch (IOException e) {
                System.out.println("Error when loading resources");
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                System.out.println("Error when closing resources file");
                e.printStackTrace();
            }
        }

        private void loadDefaultSettings() {
            this.setMin(Builder.MIN);
            this.setMax(Builder.MAX);
            this.setAttempts(Builder.ATTEMPTS);
        }

        /**
         * @param min the minimum value
         * @return this builder, for method chaining
         */
        public Builder setMin(final int min) {
            this.min = min;
            return this;
        }

        /**
         * @param max the maximum value
         * @return this builder, for method chaining
         */
        public Builder setMax(final int max) {
            this.max = max;
            return this;
        }

        /**
         * @param attempts the attempts count
         * @return this builder, for method chaining
         */
        public Builder setAttempts(final int attempts) {
            this.attempts = attempts;
            return this;
        }

        /**
         * @return a configuration
         */
        public final Configuration build(final String configFile) {
            if (consumed) {
                throw new IllegalStateException("The builder can only be used once");
            }
            consumed = true;
            if (configFile == null) {
                this.loadDefaultSettings();
            } else {
                this.loadSettings(configFile);
            }
            return new Configuration(max, min, attempts);
        }
    }
}

