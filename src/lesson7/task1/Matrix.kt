@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> = MatrixImpl(height, width, e)

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {

    private val matrixlist = mutableListOf<E>()

    init {
        if (height <= 0 || width <= 0) throw IllegalArgumentException()
        for (i in 0 until height * width)
            matrixlist.add(e)
    }

    override fun get(row: Int, column: Int): E = if (column in 0 until width && row in 0 until height) matrixlist[height * column + row]
    else throw IllegalArgumentException()

    override fun get(cell: Cell): E = matrixlist[height * cell.column + cell.row]

    override fun set(row: Int, column: Int, value: E) {
        if (column in 0 until width && row in 0 until height) matrixlist[height * column + row] = value
        else throw IllegalArgumentException()
    }

    override fun set(cell: Cell, value: E) {
        matrixlist[height * cell.column + cell.row] = value
    }

    override fun equals(other: Any?): Boolean =
            other is MatrixImpl<*> &&
                    height == other.height &&
                    width == other.width &&
                    matrixlist == other.matrixlist

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("[")
        for (row in 0 until height) {
            sb.append("[")
            for (column in 0 until width) {
                sb.append(this[row, column])
                if (column != width - 1) sb.append(" ")
            }
            sb.append("]")
        }
        sb.append("]")
        return sb.toString()
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + matrixlist.hashCode()
        return result
    }
}