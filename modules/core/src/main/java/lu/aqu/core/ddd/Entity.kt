package lu.aqu.core.ddd

interface Entity<out T : Identifier<*>> {
    val id: T
}
