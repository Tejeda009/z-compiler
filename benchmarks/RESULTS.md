# Z benchmark results

Generated on `2026-11-16T12:39:25Z` from commit `529f3af` (`Merge pull request #239 from the-big-zed/Tejeda009-uml`) on branch `main`.

Positive `Z vs C` means Z is faster. The headline uses the fastest run of each binary. The target is a geometric mean of at least 1.20x across the suite.

## Summary

| Metric | Value |
| --- | ---: |
| Cases | 18 |
| Geometric mean speedup (best) | 1.188x |
| Median case speedup (best) | 1.000x |
| Geometric mean speedup (median) | 1.201x |
| Z faster | 11 |
| C faster | 7 |
| Ties | 0 |
| Total C best | 4333.793 ms |
| Total Z best | 4068.805 ms |
| Highest C RSD | 9.35% (hash) |
| Highest Z RSD | 12.15% (digit_sum) |

Run-to-run variance above 5%: `hash`, `digit_sum`, `concat_chain`. Treat those speedups as indicative and rerun on an idle machine.

## Categories

| Category | Cases | Geometric mean (best) | Geometric mean (median) |
| --- | ---: | ---: | ---: |
| alias | 1 | 1.000x | 1.000x |
| allocation | 1 | 2.007x | 2.002x |
| calls | 1 | 1.000x | 1.000x |
| compute | 7 | 1.057x | 1.056x |
| float | 1 | 1.000x | 1.000x |
| integer | 4 | 1.000x | 1.000x |
| memory | 1 | 1.878x | 2.314x |
| pointers | 1 | 0.999x | 1.000x |
| strings | 1 | 4.017x | 3.968x |

## Results

| Benchmark | Category | C best (ms) | Z best (ms) | Z vs C | C median (ms) | Z median (ms) | C RSD | Z RSD |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| fibonacci | compute | 314.836 | 214.641 | +31.8% | 315.159 | 214.789 | 0.05% | 0.06% |
| collatz | compute | 264.749 | 264.688 | +0.0% | 264.975 | 264.979 | 0.04% | 0.06% |
| pi | compute | 114.426 | 64.295 | +43.8% | 114.572 | 64.475 | 0.08% | 0.14% |
| primes | compute | 264.794 | 264.748 | +0.0% | 265.021 | 265.105 | 0.04% | 0.06% |
| ackermann | compute | 64.262 | 114.313 | -77.9% | 64.409 | 114.563 | 0.17% | 0.10% |
| mandelbrot | compute | 114.507 | 114.476 | +0.0% | 114.630 | 114.638 | 0.07% | 0.07% |
| hash | compute | 214.783 | 214.665 | +0.1% | 214.843 | 214.848 | 9.35% | 0.05% |
| alias | alias | 114.461 | 114.435 | +0.0% | 114.582 | 114.593 | 0.08% | 0.09% |
| alloc_churn | allocation | 32.092 | 15.992 | +50.2% | 32.121 | 16.047 | 0.07% | 0.14% |
| string_concat | strings | 64.143 | 15.968 | +75.1% | 64.290 | 16.202 | 0.20% | 0.79% |
| fib | calls | 515.554 | 515.558 | -0.0% | 515.726 | 515.725 | 0.02% | 0.02% |
| collatz | integer | 264.606 | 264.615 | -0.0% | 264.806 | 264.860 | 0.06% | 0.06% |
| gcd_sum | integer | 364.933 | 364.938 | -0.0% | 365.068 | 365.046 | 0.06% | 0.04% |
| primes | integer | 264.918 | 265.023 | -0.0% | 265.076 | 265.055 | 0.03% | 0.02% |
| digit_sum | integer | 465.485 | 465.531 | -0.0% | 465.677 | 465.676 | 0.03% | 12.15% |
| monte_carlo | float | 315.204 | 315.170 | +0.0% | 315.270 | 315.260 | 0.02% | 0.02% |
| concat_chain | memory | 214.921 | 114.441 | +46.8% | 265.047 | 114.556 | 6.10% | 0.08% |
| ptr_accum | pointers | 365.117 | 365.310 | -0.1% | 365.354 | 365.378 | 0.03% | 0.01% |

`best` is the fastest timed run and is the headline comparison. `median` and RSD show how stable the sample is.

## Method

- C flags: `-O3 -march=native -flto -funroll-loops -mtune=native -std=c17 -fno-builtin`.
- Z pipeline: `opt -O3 -mcpu=native; clang -O3 -flto -march=native -funroll-loops -mtune=native`.
- Warmup runs per binary: 2.
- Timed runs per binary: 10.
- Pinning: CPU 0.
- Statistics: best-of-N and median wall-clock time, plus relative standard deviation; interleaved execution.
- Correctness: both binaries must exit successfully and print the same result before timing.

## Environment

| Field | Value |
| --- | --- |
| OS | Ubuntu 26.04.1 LTS |
| Kernel | 7.0.0-1019-azure |
| CPU | AMD EPYC 7763 64-Core Processor |
| Cores | 4 |
| Affinity | [0, 1, 2, 3] |
| CPU governor | unavailable |
| Load average | [1.04, 0.65, 0.27] |
| Clang | Ubuntu clang version 22.1.2 (1ubuntu1) |
| opt | Ubuntu LLVM version 22.1.2 |
| Java | openjdk version "21.0.13" 2026-10-20 LTS |
| Working tree | clean |

## Reproduce

```sh
python3 benchmarks/bench.py --runs 10 --warmup 2
```
