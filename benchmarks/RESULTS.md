# Z benchmark results

Generated on `2026-11-19T15:58:05Z` from commit `6cf30d8` (`Merge pull request #246 from the-big-zed/benchmarks/results`) on branch `main`.

Positive `Z vs C` means Z is faster. The headline uses the fastest run of each binary. The target is a geometric mean of at least 1.20x across the suite.

## Summary

| Metric | Value |
| --- | ---: |
| Cases | 18 |
| Geometric mean speedup (best) | 1.189x |
| Median case speedup (best) | 1.000x |
| Geometric mean speedup (median) | 1.202x |
| Z faster | 13 |
| C faster | 5 |
| Ties | 0 |
| Total C best | 4327.283 ms |
| Total Z best | 4062.465 ms |
| Highest C RSD | 13.82% (alloc_churn) |
| Highest Z RSD | 28.57% (string_concat) |

Run-to-run variance above 5%: `pi`, `alloc_churn`, `string_concat`, `digit_sum`, `concat_chain`. Treat those speedups as indicative and rerun on an idle machine.

## Categories

| Category | Cases | Geometric mean (best) | Geometric mean (median) |
| --- | ---: | ---: | ---: |
| alias | 1 | 1.000x | 1.000x |
| allocation | 1 | 2.022x | 2.010x |
| calls | 1 | 1.000x | 1.000x |
| compute | 7 | 1.056x | 1.056x |
| float | 1 | 1.000x | 1.000x |
| integer | 4 | 1.000x | 1.000x |
| memory | 1 | 1.878x | 2.313x |
| pointers | 1 | 1.000x | 1.000x |
| strings | 1 | 4.075x | 4.002x |

## Results

| Benchmark | Category | C best (ms) | Z best (ms) | Z vs C | C median (ms) | Z median (ms) | C RSD | Z RSD |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| fibonacci | compute | 314.554 | 214.369 | +31.8% | 314.989 | 214.752 | 0.06% | 0.07% |
| collatz | compute | 264.453 | 264.457 | -0.0% | 264.775 | 264.789 | 2.95% | 2.64% |
| pi | compute | 114.074 | 63.985 | +43.9% | 114.447 | 64.296 | 0.10% | 5.50% |
| primes | compute | 264.587 | 264.507 | +0.0% | 264.881 | 264.880 | 0.06% | 0.05% |
| ackermann | compute | 64.084 | 114.191 | -78.2% | 64.337 | 114.461 | 0.25% | 0.09% |
| mandelbrot | compute | 114.162 | 114.290 | -0.1% | 114.509 | 114.517 | 0.07% | 0.06% |
| hash | compute | 214.407 | 214.356 | +0.0% | 214.702 | 214.687 | 2.32% | 0.06% |
| alias | alias | 114.193 | 114.185 | +0.0% | 114.420 | 114.433 | 0.10% | 0.09% |
| alloc_churn | allocation | 31.712 | 15.686 | +50.5% | 31.950 | 15.895 | 13.82% | 0.73% |
| string_concat | strings | 63.853 | 15.669 | +75.5% | 64.190 | 16.040 | 5.53% | 28.57% |
| fib | calls | 514.979 | 514.930 | +0.0% | 515.520 | 515.492 | 1.53% | 4.99% |
| collatz | integer | 264.491 | 264.456 | +0.0% | 264.823 | 264.820 | 1.88% | 1.88% |
| gcd_sum | integer | 364.740 | 364.647 | +0.0% | 365.166 | 365.155 | 0.05% | 0.04% |
| primes | integer | 264.483 | 264.517 | -0.0% | 264.886 | 264.880 | 0.06% | 0.05% |
| digit_sum | integer | 464.823 | 464.763 | +0.0% | 465.050 | 465.351 | 3.21% | 8.62% |
| monte_carlo | float | 314.606 | 314.595 | +0.0% | 315.015 | 315.014 | 0.05% | 0.05% |
| concat_chain | memory | 214.383 | 114.153 | +46.8% | 264.684 | 114.432 | 6.18% | 5.30% |
| ptr_accum | pointers | 364.698 | 364.709 | -0.0% | 365.095 | 365.096 | 0.05% | 3.87% |

`best` is the fastest timed run and is the headline comparison. `median` and RSD show how stable the sample is.

## Method

- C flags: `-O3 -march=native -flto -funroll-loops -mtune=native -std=c17 -fno-builtin`.
- Z pipeline: `opt -O3 -mcpu=native; clang -O3 -flto -march=native -funroll-loops -mtune=native`.
- Warmup runs per binary: 2.
- Timed runs per binary: 200.
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
| Load average | [1.1, 1.0, 0.91] |
| Clang | Ubuntu clang version 22.1.2 (1ubuntu1) |
| opt | Ubuntu LLVM version 22.1.2 |
| Java | openjdk version "21.0.13" 2026-10-20 LTS |
| Working tree | clean |

## Reproduce

```sh
python3 benchmarks/bench.py --runs 200 --warmup 2
```
