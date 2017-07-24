package project.neutrino.library.repository;

public class RepositoryFactory {

    public static <T> T create(Class<T> clazz) {
        return ConnectionManager
                .getManager()
                .dbi.open(clazz);
    }
}
