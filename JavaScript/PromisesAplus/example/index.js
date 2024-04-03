const promisesAplusTests = require('promises-aplus-tests')
const MyPromise = require('./MyPromise')

function deferred() {
    const res = {}
    res.promise = new MyPromise((resolve, reject) => {
        res.resolve = resolve
        res.reject = reject
    })
    return res
}

promisesAplusTests({ deferred }, function (err) {
    console.log(err)
})
