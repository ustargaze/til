const PENDING = 'pending'
const FULFILLED = 'fulfilled'
const REJECTED = 'rejected'

class MyPromise {
    constructor(executor) {
        this.state = PENDING
        this.onFulfilledCallbacks = []
        this.onRejectedCallbacks = []
        this.value = undefined
        this.reason = undefined

        function resolve(value) {
            if (this.state === PENDING) {
                this.value = value
                this.state = FULFILLED
                // 异步地执行所有的 onFulfilled
                this.onFulfilledCallbacks.forEach((callback) => process.nextTick(callback.bind(null, value)))
            }
        }

        function reject(reason) {
            if (this.state === PENDING) {
                this.reason = reason
                this.state = REJECTED
                // 异步地执行所有的 onRejected
                this.onRejectedCallbacks.forEach((callback) => process.nextTick(callback.bind(null, reason)))
            }
        }

        // 同步执行 executor
        executor(resolve.bind(this), reject.bind(this))
    }

    then(onFulfilled, onRejected) {
        let promise2 = new MyPromise((resolve, reject) => {
            const _onFulfilled = (value) => {
                // 如果 onFulfilled 是函数使用其返回值来解决 promise2，如果执行出现异常使用异常拒绝 promise2
                if (typeof onFulfilled === 'function') {
                    try {
                        value = onFulfilled(value)
                    } catch (e) {
                        reject(e)
                        return
                    }
                }
                // onFulfilled 不是函数使用传入的 value（onFulfilled 关联的 promise 的 value） 来解决 promise2
                resolveMyPromise(promise2, value, resolve, reject)
            }
            const _onRejected = (reason) => {
                // 如果 onRejected 是函数使用其返回值来解决 promise2，如果执行出现异常使用异常拒绝 promise2
                if (typeof onRejected === 'function') {
                    try {
                        const value = onRejected(reason)
                        resolveMyPromise(promise2, value, resolve, reject)
                        return
                    } catch (e) {
                        reason = e
                    }
                }
                // onRejected 不是函数使用传入的 reason（onRejected 关联的 promise 的 reason） 来拒绝 promise2
                reject(reason)
            }

            // 如果当前 promise 是 pending 直接将 _onFulfilled、_onRejected 添加到对应到队列中
            // 否则根据响应状态，将指定回调绑定相关参数然后使用 process.nextTick 添加异步队列中执行
            if (this.state === FULFILLED) {
                process.nextTick(_onFulfilled.bind(null, this.value))
            } else if (this.state === REJECTED) {
                process.nextTick(_onRejected.bind(null, this.reason))
            } else {
                this.onFulfilledCallbacks.push(_onFulfilled)
                this.onRejectedCallbacks.push(_onRejected)
            }
        })
        return promise2
    }
}

function resolveMyPromise(myPromise, x, resolve, reject) {
    // promise 和 x 为同一对象用 TypeError 拒绝
    if (myPromise === x) {
        reject(new TypeError('Don\'t use the promise to resolve itself.'))
    }
    if (x instanceof MyPromise) {
        // x 为 promise，promise 的状态由 x 的状态决定并且终值或者拒因也与 x 一致。
        x.then(
            (y) => resolveMyPromise(myPromise, y, resolve, reject),
            (r) => reject(r)
        )
    } else if (x != null && (typeof x === 'function' || typeof x === 'object')) {
        // x 是函数或者对象，获取其 then 函数，获取 then 出现异常，用异常拒绝 promise
        let then
        try {
            then = x.then
        } catch (e) {
            reject(e)
        }

        if (typeof then === 'function') {
            // 以 x 为 this 调用 then
            // 使用 called 参数保证 then 方法中 onFulfilled 和 onRejected 两者只执行其中一个且仅会执行一次
            let called = false
            try {
                then.call(
                    x,
                    // 传入 resolvePromise 获取 then 的返回值来解决 promise
                    function resolvePromise(y) {
                        if (called) return
                        called = true
                        resolveMyPromise(myPromise, y, resolve, reject)
                    },
                    // 传入 rejectPromise 获取 then 的拒因 r 来拒绝 promise
                    function rejectPromise(r) {
                        if (called) return
                        called = true
                        reject(r)
                    }
                )
            } catch (e) {
                if (!called) {
                    reject(e)
                }
            }
        } else {
            // 不是函数使用 x 解决 promise
            resolve(x)
        }
    } else {
        // 非函数、非对象使用 x 解决 promise
        resolve(x)
    }
}

module.exports = MyPromise
