# SADI-Core-Codebase-Jena3
Original SADI-Core-Codebase updated to work with Jena3

Considerations in Jena3:

Packages with a base name of com.hp.hpl.jena become org.apache.jena
Global replacement of import com.hp.hpl.jena. with import org.apache.jena

GraphStore interface has been removed
ModelFactory.createFileModelMaker has been removed 
LateBindingIterator has been removed: use LazyIterator instead
EarlyBindingIterator has been removed: no replacement
UniqueExtendedIterator has been removed: use ExtendedIterator with unique filter

Others secundary libraries were incompatibles with Jena3, most of them were replaced for others compatibles.
Main behaviour of SADI-SHARE tried with virtuoso registry is perfect.
